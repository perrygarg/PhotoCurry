package com.tsystems.photocurry.home.model;

import com.google.gson.annotations.SerializedName;
import com.tsystems.photocurry.common.network.WebConstants;

import java.io.Serializable;

/**
 * Created by PerryGarg on 10/05/18.
 */
public class Image implements Serializable {

    @SerializedName("id")
    public String id;

    @SerializedName("farm")
    public int farmId;

    @SerializedName("server")
    public String serverId;

    @SerializedName("secret")
    public String secret;

    @SerializedName("owner")
    public String ownerId;

    @SerializedName("title")
    public String title;

    public Image() {
    }

    public Image(String id, int farmId, String serverId, String secret, String ownerId, String title) {
        this.id = id;
        this.farmId = farmId;
        this.serverId = serverId;
        this.secret = secret;
        this.ownerId = ownerId;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmallSizeUrl() {
        return WebConstants.FLICKR_IMG_1 + getFarmId() + WebConstants.FLICKR_IMG_2 + getServerId() + WebConstants.SEPARATOR + getId() + WebConstants.UNDERSCORE + getSecret() + WebConstants.SMALL + WebConstants.FLICKR_IMG_3;
    }

    public String getMediumSizeUrl() {
        return WebConstants.FLICKR_IMG_1 + getFarmId() + WebConstants.FLICKR_IMG_2 + getServerId() + WebConstants.SEPARATOR + getId() + WebConstants.UNDERSCORE + getSecret() + WebConstants.MEDIUM + WebConstants.FLICKR_IMG_3;
    }

    public String getLargeSizeUrl() {
        return WebConstants.FLICKR_IMG_1 + getFarmId() + WebConstants.FLICKR_IMG_2 + getServerId() + WebConstants.SEPARATOR + getId() + WebConstants.UNDERSCORE + getSecret() + WebConstants.LARGE + WebConstants.FLICKR_IMG_3;
    }

}
