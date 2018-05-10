package com.tsystems.photocurry.home.model;

import java.io.Serializable;

/**
 * Created by PerryGarg on 10/05/18.
 */
public class Image implements Serializable {
    private String name;
    private String small, medium, large;
    private String timestamp;

    public Image() {
    }

    public Image(String name, String small, String medium, String large, String timestamp) {
        this.name = name;
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmallVersionUrl() {
        return small;
    }

    public void setSmallVersionUrl(String small) {
        this.small = small;
    }

    public String getMediumVersionUrl() {
        return medium;
    }

    public void setMediumVersionUrl(String medium) {
        this.medium = medium;
    }

    public String getLargeVersionUrl() {
        return large;
    }

    public void setLargeVersionUrl(String large) {
        this.large = large;
    }
}
