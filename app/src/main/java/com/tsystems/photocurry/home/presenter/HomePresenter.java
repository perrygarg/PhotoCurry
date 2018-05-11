package com.tsystems.photocurry.home.presenter;

import com.tsystems.photocurry.common.model.MasterResponse;
import com.tsystems.photocurry.common.model.ValidationError;
import com.tsystems.photocurry.common.network.WebConstants;
import com.tsystems.photocurry.common.network.WebManager;
import com.tsystems.photocurry.common.network.WebService;
import com.tsystems.photocurry.common.network.WebServiceListener;
import com.tsystems.photocurry.common.util.AppUtil;
import com.tsystems.photocurry.home.contract.HomeContract;
import com.tsystems.photocurry.home.model.Image;
import com.tsystems.photocurry.home.model.SearchPhotoResponse;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by PerryGarg on 11-05-2018.
 */

public class HomePresenter implements HomeContract.Presenter, WebServiceListener {
    private HomeContract.View view = null;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void fetchPhotosWithQuery(String queryText) {
        if(validateInput(queryText)) {
            doFetchPhotosWithQuery(queryText);
        }
    }

    /**
     * Method to call API to fetch images against query text
     * @param queryText Query text
     */
    private void doFetchPhotosWithQuery(String queryText) {
        WebService webService = WebManager.getService(WebConstants.WS_QUERY_IMAGE_SERVICE, this);
        webService.getData(queryText);
    }

    private boolean validateInput(String queryText) {
        return AppUtil.isStringEmpty(queryText) ? false : true;
    }

    @Override
    public void onServiceBegin(int taskCode) {
        view.showProgress(taskCode);
    }

    @Override
    public void onServiceSuccess(MasterResponse masterResponse, int taskCode) {
        view.hideProgress(taskCode);

        SearchPhotoResponse response = (SearchPhotoResponse) masterResponse;
        int totalImages = response.photos.totalPhotos;
        Image[] images = response.photos.images;
        ArrayList<Image> imagesList = new ArrayList<Image>(Arrays.asList(images));
        view.onFetchPhotosSuccess(imagesList, totalImages);
    }

    @Override
    public void onServiceError(String message, int taskCode, int errorType) {
        view.hideProgress(taskCode);
        view.showError(message);
    }

    @Override
    public void onValidationError(ValidationError[] validationError, int taskCode) {

    }
}
