package com.tsystems.photocurry.home.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PerryGarg on 10-05-2018.
 */

public class Photos {

    @SerializedName("page")
    public int page;

    @SerializedName("pages")
    public int totalPages;

    @SerializedName("perpage")
    public int perpage;

    @SerializedName("total")
    public int totalPhotos;

    @SerializedName("photo")
    public Image[] images;

}
