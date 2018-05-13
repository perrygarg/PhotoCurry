package com.tsystems.photocurry.home.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tsystems.photocurry.home.model.Image;
import com.tsystems.photocurry.home.model.Images;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by PerryGarg on 13-05-2018.
 */

public class Converters implements Serializable {

    @TypeConverter
    public static Images fromString(String value) {
        Type listType = new TypeToken<Images>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(Images images) {
        Gson gson = new Gson();
        String json = gson.toJson(images);
        return json;
    }

}
