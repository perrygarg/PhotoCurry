package com.tsystems.photocurry.home.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tsystems.photocurry.R;
import com.tsystems.photocurry.common.activity.BaseActivity;
import com.tsystems.photocurry.common.adapter.BaseRecyclerAdapterListener;
import com.tsystems.photocurry.common.util.AppUtil;
import com.tsystems.photocurry.common.util.UIUtil;
import com.tsystems.photocurry.home.adapter.ImagesListAdapter;
import com.tsystems.photocurry.home.contract.HomeContract;
import com.tsystems.photocurry.home.model.Image;
import com.tsystems.photocurry.home.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity
        implements HomeContract.View, BaseRecyclerAdapterListener, View.OnClickListener {

    private String TAG = HomeActivity.class.getSimpleName();
    private RecyclerView imagesRecView;
    private ProgressDialog progressDialog;
    private ImagesListAdapter imagesListAdapter;
    private List<Image> images;
    private HomePresenter presenter;
    private ConstraintLayout parentView;

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

        fetchImagesAgainstQuery("superbike");
    }

    private void initializeObjects() {
        presenter = new HomePresenter(this);
    }

    private void fetchImagesAgainstQuery(String queryText) {
        presenter.fetchPhotosWithQuery(queryText.trim().toLowerCase());
    }

    private void setupRecyclerView() {
        images = new ArrayList<>();
        imagesListAdapter = new ImagesListAdapter(this, this, images);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
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
        if(progressDialog == null || !progressDialog.isShowing())
            progressDialog = UIUtil.showProgressDialog(this, "Hold On...", "Fetching images for you", false, false);
    }

    @Override
    public void hideProgress(int processCode) {
        UIUtil.dismissDialog(progressDialog);
    }

    @Override
    public void showError(String errMsg) {
        if(AppUtil.isStringEmpty(errMsg))
            errMsg = getString(R.string.error_tech);

        UIUtil.showSnackbar(parentView, errMsg, false);
    }

    @Override
    public void showSnackBarMessage(String msg) {

    }

    @Override
    public void onFetchPhotosSuccess(List<Image> images, int totalImages) {
        this.images.addAll(images);
        imagesListAdapter.notifyDataSetChanged();
        imagesListAdapter.setTotal(totalImages);
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void handleError() {

    }

    @Override
    public void onClick(View view) {

    }
}
