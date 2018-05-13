package com.tsystems.photocurry.home.presenter;

import com.tsystems.photocurry.R;
import com.tsystems.photocurry.application.MyApplication;
import com.tsystems.photocurry.common.constants.AppConstants;
import com.tsystems.photocurry.common.model.MasterResponse;
import com.tsystems.photocurry.common.model.ValidationError;
import com.tsystems.photocurry.common.network.WebConstants;
import com.tsystems.photocurry.common.network.WebManager;
import com.tsystems.photocurry.common.network.WebService;
import com.tsystems.photocurry.common.network.WebServiceListener;
import com.tsystems.photocurry.common.util.AppUtil;
import com.tsystems.photocurry.home.contract.HomeContract;
import com.tsystems.photocurry.home.model.Image;
import com.tsystems.photocurry.home.model.Images;
import com.tsystems.photocurry.home.model.SearchEntity;
import com.tsystems.photocurry.home.model.SearchPhotoResponse;
import com.tsystems.photocurry.home.model.SearchRequest;
import com.tsystems.photocurry.home.repository.SearchEntityRepository;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by PerryGarg on 11-05-2018.
 */

/**
 * Presenter class to interact with activity and service classes
 */
public class HomePresenter implements HomeContract.Presenter, WebServiceListener, DatabaseListener {
    WeakReference<HomeContract.View> viewWeakReference;
    SearchEntityRepository searchEntityRepository;
    private String queryText;

    /**
     * Constructor of presenter which initializes Weak Reference of the View. It should be called from relevant activity.
     * @param view view instance
     */
    public HomePresenter(HomeContract.View view) {
        viewWeakReference = new WeakReference<>(view);
        searchEntityRepository = new SearchEntityRepository(MyApplication.getAppInstance(), this);
    }

    @Override
    public void fetchPhotosWithQuery(int pageNumber, int photosPerPage, String queryText) {
        if(validateInput(queryText)) {
            doFetchPhotosWithQuery(pageNumber, photosPerPage, queryText);
        } else {
            if(!this.isViewObjectNull()) {
                viewWeakReference.get().showSnackBarMessage(MyApplication.appContext.getString(R.string.invalid_query));
            }
        }
    }

    @Override
    public void cacheResponseInDatabase(List<Image> images) {
        Images imageCollection = new Images(images);
        SearchEntity searchEntity = new SearchEntity(queryText, imageCollection);
        searchEntityRepository.insert(searchEntity);
    }

    /**
     * Method to call API to fetch images against query text
     * @param pageNumber Page Number
     * @param photosPerPage Photos Per Page
     * @param queryText Query text
     */
    private void doFetchPhotosWithQuery(int pageNumber, int photosPerPage, String queryText) {
        SearchRequest request = new SearchRequest();
        request.pageNumber = pageNumber;
        request.photosPerPage = photosPerPage;
        request.queryText = queryText;
        this.queryText = queryText;

        WebService webService = WebManager.getService(WebConstants.WS_QUERY_IMAGE_SERVICE, this);
        webService.getData(request);

    }

    private boolean validateInput(String queryText) {
        return AppUtil.isStringEmpty(queryText) ? false : true;
    }

    @Override
    public void onServiceBegin(int taskCode) {
        if(!this.isViewObjectNull()) {
            viewWeakReference.get().showProgress(taskCode);
        }
    }

    @Override
    public void onServiceSuccess(MasterResponse masterResponse, int taskCode) {
        if(!this.isViewObjectNull()) {
            viewWeakReference.get().hideProgress(taskCode);
        }

        SearchPhotoResponse response = (SearchPhotoResponse) masterResponse;
        int totalImages = response.photos.totalPhotos;
        Image[] images = response.photos.images;
        ArrayList<Image> imagesList = new ArrayList(Arrays.asList(images));

        if(!this.isViewObjectNull()) {
            viewWeakReference.get().onFetchPhotosSuccess(imagesList, totalImages);
        }

    }

    @Override
    public void onServiceError(String message, int taskCode, int errorType) {
        if(errorType == AppConstants.ERROR_TYPE_NO_NETWORK) {
            hitDatabase();
        } else {
            if(!this.isViewObjectNull()) {
                viewWeakReference.get().hideProgress(taskCode);
                viewWeakReference.get().showError(message);
            }
        }
    }

    private void hitDatabase() {
        searchEntityRepository.getSearchEntity(queryText);
    }

    @Override
    public void onValidationError(ValidationError[] validationError, int taskCode) {

    }

    /**
     * Method to check if weak reference of the view is null. Usable only from this Presenter.
     * @return true if {@link #viewWeakReference} is null, false otherwise
     */
    private boolean isViewObjectNull() {
        if(viewWeakReference == null) {
            return true;
        } else {
            if(viewWeakReference.get() == null) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void onDBServiceSuccess(SearchEntity searchEntity) {
        if(!this.isViewObjectNull()) {
            if(searchEntity == null) {
                onServiceError(MyApplication.appContext.getString(R.string.error_network), -1, AppConstants.EMPTY_RESULT_FROM_DB);
                viewWeakReference.get().hideProgress(0);
                viewWeakReference.get().onFetchPhotosSuccess(new ArrayList<Image>(), 0);
            } else {
                viewWeakReference.get().hideProgress(0);
                viewWeakReference.get().onFetchPhotosSuccess(searchEntity.getImages().getImageList(), searchEntity.getImages().getImageList().size());
            }
        }
    }
}
