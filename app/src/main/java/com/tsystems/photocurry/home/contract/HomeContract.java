package com.tsystems.photocurry.home.contract;

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
    }

    interface Presenter {

    }

}
