package com.tsystems.photocurry.home.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.tsystems.photocurry.common.constants.AppConstants;
import com.tsystems.photocurry.home.converters.Converters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PerryGarg on 13-05-2018.
 */

/**
 * Entity class for Room
 */
@Entity(tableName = AppConstants.SEARCH_TABLE)
public class SearchEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = AppConstants.QUERY)
    private String queryText;

    @ColumnInfo(name = AppConstants.IMAGES)
    private Images images;

    public SearchEntity(@NonNull String queryText, @NonNull Images images) {
        this.queryText = queryText;
        this.images = images;
    }

    public String getQueryText() {
        return this.queryText;
    }

    public Images getImages() {
        return this.images;
    }

}
