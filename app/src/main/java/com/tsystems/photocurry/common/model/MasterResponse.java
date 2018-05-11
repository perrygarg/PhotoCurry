package com.tsystems.photocurry.common.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PerryGarg on 10/05/18.
 */

/**
 * Master response for Flickr APIs
 * Uses GSON for JSON parsing into java models
 */
public class MasterResponse {

    @SerializedName("stat")
    public String status;

}