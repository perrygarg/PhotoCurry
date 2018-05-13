package com.tsystems.photocurry.home.presenter;

import com.tsystems.photocurry.home.model.SearchEntity;

/**
 * Created by PerryGarg on 13-05-2018.
 */

public interface DatabaseListener {

    /**
     * Method to deliver data from DB to presenter
     * @param searchEntity search entity to be transferred
     */
    void onDBServiceSuccess(SearchEntity searchEntity);

}
