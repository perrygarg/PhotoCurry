package com.tsystems.photocurry.common.network;

import com.google.gson.annotations.SerializedName;
import com.tsystems.photocurry.common.model.MasterResponse;

/**
 * Created by rahul.sood on 2/17/2017.
 */

public class ExternalJsonObject extends MasterResponse {

    @SerializedName("object")
    public MasterResponse object;

}
