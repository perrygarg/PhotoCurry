package com.tsystems.photocurry.common.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by perry.garg on 12/05/18.
 */

public class ValidationError {
    @SerializedName("param")
    public String param = null;

    @SerializedName("msg")
    public String msg = null;

    @SerializedName("value")
    public String value = null;

    @SerializedName("code")
    public String code = null;
}
