package com.tsystems.photocurry.home.model;

import com.google.gson.annotations.SerializedName;
import com.tsystems.photocurry.common.constants.AppConstants;
import com.tsystems.photocurry.common.model.MasterResponse;

/**
 * Created by PerryGarg on 10-05-2018.
 */

public class SearchPhotoResponse extends MasterResponse {

    @SerializedName(AppConstants.PHOTOS)
    public Photos photos;

}
