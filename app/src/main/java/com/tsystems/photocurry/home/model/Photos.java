package com.tsystems.photocurry.home.model;

import com.google.gson.annotations.SerializedName;
import com.tsystems.photocurry.common.constants.AppConstants;

/**
 * Created by PerryGarg on 10-05-2018.
 */

public class Photos {

    @SerializedName(AppConstants.PAGE)
    public int page;

    @SerializedName(AppConstants.PAGES)
    public int totalPages;

    @SerializedName(AppConstants.PERPAGE)
    public int perpage;

    @SerializedName(AppConstants.TOTAL)
    public int totalPhotos;

    @SerializedName(AppConstants.PHOTO)
    public Image[] images;

}
