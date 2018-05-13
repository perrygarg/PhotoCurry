package com.tsystems.photocurry.home.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tsystems.photocurry.R;
import com.tsystems.photocurry.common.activity.BaseActivity;
import com.tsystems.photocurry.common.adapter.BaseRecyclerAdapterListener;
import com.tsystems.photocurry.common.constants.AppConstants;
import com.tsystems.photocurry.common.listeners.OnItemClickListener;
import com.tsystems.photocurry.common.util.AppUtil;
import com.tsystems.photocurry.common.util.UIUtil;
import com.tsystems.photocurry.home.adapter.ImagesListAdapter;
import com.tsystems.photocurry.home.contract.HomeContract;
import com.tsystems.photocurry.home.model.Image;
import com.tsystems.photocurry.home.presenter.HomePresenter;
import com.tsystems.photocurry.viewimage.activity.ViewImageActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity
        implements HomeContract.View, BaseRecyclerAdapterListener, View.OnClickListener, OnItemClickListener {

    private String TAG = HomeActivity.class.getSimpleName();
    private RecyclerView imagesRecView;
    private ProgressDialog progressDialog;
    private ImagesListAdapter imagesListAdapter;
    private List<Image> images;
    private HomePresenter presenter;
    private RelativeLayout parentView;
    private int spanCount = 2;
    private EditText searchEditText;
    private Button doneButton;
    private GridLayoutManager mLayoutManager;
    private int pageNumber = 1;
    private int photosPerPage = 30; //For best user experience, we can set this number dynamically on basis of screen size available on current device. Hardcoding to 30 for short term.
    private String queryText = "";
    private View emptyView, progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    /**
     * Method for initiating activity components. Should be called from onCreate() of current activity
     */
    private void init() {
        configureToolbar();
        findViewsByIds();
        setupRecyclerView();
        initializeObjects();
        setListeners();
    }

    private void setListeners() {
        doneButton.setOnClickListener(this);
    }

    private void initializeObjects() {
        presenter = new HomePresenter(this);
    }

    private void fetchImagesAgainstQuery(String queryText) {
        presenter.fetchPhotosWithQuery(pageNumber, photosPerPage, queryText.trim().toLowerCase());
    }

    private void setupRecyclerView() {
        images = new ArrayList<>();
        imagesListAdapter = new ImagesListAdapter(this, this, images, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), spanCount);
        this.mLayoutManager = (GridLayoutManager) mLayoutManager;
        ((GridLayoutManager)mLayoutManager).setSpanSizeLookup(spanSizeLookup);
        imagesRecView.setLayoutManager(mLayoutManager);
        imagesRecView.setItemAnimator(new DefaultItemAnimator());
        imagesRecView.setAdapter(imagesListAdapter);
    }

    /**
     * Method responsible for importing all views into java code for this activity
     * Should be called from {@link #init()}
     */
    private void findViewsByIds() {
        imagesRecView = findViewById(R.id.images_recycler_view);
        parentView = findViewById(R.id.parent_view);
        searchEditText = findViewById(R.id.search_edit_text);
        doneButton = findViewById(R.id.done_btn);
        emptyView = findViewById(R.id.empty_view);
        progressView = findViewById(R.id.progress_view);
    }

    /**
     * Method to configure toolbar for current activity
     */
    private void configureToolbar() {
        setupToolbar(getString(R.string.app_name), false);
        setToolbarBackgroundColor();
    }

    @Override
    public void showProgress(int processCode) {
//        if(progressDialog == null || !progressDialog.isShowing()) {
            if(isFirstPage()) {
//                progressDialog = UIUtil.showProgressDialog(this, "Please wait", "Fetching smile for you", true, false);
                hideEmptyViewIfRequired();
                progressView.setVisibility(View.VISIBLE);
            }
//        }
    }

    private void hideEmptyViewIfRequired() {
        emptyView.setVisibility(View.GONE);
    }

    private boolean isFirstPage() {
        return pageNumber == 1 ? true : false;
    }

    @Override
    public void hideProgress(int processCode) {
//        UIUtil.dismissDialog(progressDialog);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errMsg) {
        if(AppUtil.isStringEmpty(errMsg))
            errMsg = getString(R.string.error_tech);

        UIUtil.showSnackbar(parentView, errMsg, false);
    }

    @Override
    public void showSnackBarMessage(String msg) {
        showError(msg);
    }

    @Override
    public void onFetchPhotosSuccess(List<Image> images, int totalImages) {
        this.images.addAll(images);
        imagesListAdapter.notifyDataSetChanged();
        imagesListAdapter.setTotal(totalImages);
        if(this.images.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }

        presenter.cacheResponseInDatabase(this.images);
    }

    @Override
    public void loadMore() {
        pageNumber++;
        presenter.fetchPhotosWithQuery(pageNumber, photosPerPage, queryText);
    }

    @Override
    public void handleError() {

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.done_btn:
                clickOnDoneButton();
                break;
        }
    }

    private void clickOnDoneButton() {
        resetData();

        String queryText = searchEditText.getText().toString();
        this.queryText = queryText;
        fetchImagesAgainstQuery(queryText);
    }

    private void resetData() {
        images.clear();
        UIUtil.hideSoftKeyboard(this);
        pageNumber = 1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.grid_span_count_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.size2:
                spanCount = 2;
                mLayoutManager.setSpanCount(spanCount);
                return true;

            case R.id.size3:
                spanCount = 3;
                mLayoutManager.setSpanCount(spanCount);
                return true;

            case R.id.size4:
                spanCount = 4;
                mLayoutManager.setSpanCount(spanCount);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Inner class to handle pagination progress bar in grid view.
     */
    GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            switch(imagesListAdapter.getItemViewType(position)){
                case AppConstants.ITEM_SEARCH_IMAGE:
                    return 1;
                case AppConstants.ITEM_PROGRESS_SHOWN:
                    return spanCount;
                default:
                    return -1;
            }
        }
    };

    @Override
    public void onItemClick(int position, View view) {
        Image image = images.get(position);
        navigateToViewImageActivity(position, (ImageView) view);
    }

    private void navigateToViewImageActivity(int position, ImageView view) {
        Intent intent = new Intent(HomeActivity.this, ViewImageActivity.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this, view, ViewCompat.getTransitionName(view));
        intent.putExtra(AppConstants.IMAGE_URL, images.get(position).getMediumSizeUrl());
        intent.putExtra(AppConstants.IMAGE_URL_MEDIUM, images.get(position).getSmallSizeUrl());
        startActivity(intent, optionsCompat.toBundle());
    }
}
