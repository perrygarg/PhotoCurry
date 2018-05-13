package com.tsystems.photocurry.home.model;

import java.util.List;

/**
 * Created by PerryGarg on 13-05-2018.
 */

public class Images {

    private List<Image> imageList;

    public Images(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Image> getImageList() {
        return imageList;
    }

}
