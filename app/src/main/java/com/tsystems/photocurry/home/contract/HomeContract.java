package com.tsystems.photocurry.home.contract;

import com.tsystems.photocurry.home.model.Image;

import java.util.List;

/**
 * Created by PerryGarg on 10-05-2018.
 */

public interface HomeContract {

    interface View {
        /**
         * Method to show progress to the user. Should be called from Presenter.
         * @param processCode Task code for which progress need to be displayed
         */
        void showProgress(int processCode);

        /**
         * Method to hide progress. Should be called from Presenter.
         * @param processCode Task code for which progress need to be hide
         */
        void hideProgress(int processCode);

        /**
         * Method to show any kind of error to the user in the dialog.
         * @param errMsg Error message to be shown
         */
        void showError(String errMsg);

        /**
         * Method to show snackbar message.
         * @param msg Message to be displayed
         */
        void showSnackBarMessage(String msg);

        /**
         * Method to call when images fetched from API needs to be delivered to the UI. Should be called from Presenter.
         * @param images Images list
         * @param totalImages Total number of available images in the API
         */
        void onFetchPhotosSuccess(List<Image> images, int totalImages);
    }

    interface Presenter {

        /**
         * Method used to call API to fetch images with a specific query text
         * @param queryText Query text
         */
        void fetchPhotosWithQuery(String queryText);

    }

}
