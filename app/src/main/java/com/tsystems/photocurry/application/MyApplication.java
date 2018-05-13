package com.tsystems.photocurry.application;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.request.target.ViewTarget;
import com.tsystems.photocurry.R;

/**
 * Created by PerryGarg on 10-05-2018.
 */

public class MyApplication extends Application {

    public static Context appContext;
    public static MyApplication myApplication;

    @Override
    public void onCreate()
    {
        super.onCreate();

        //known issue of Glide library
        ViewTarget.setTagId(R.id.glide_tag);

        //Prepare Application
        appContext = this;
        myApplication = this;
    }

    public static MyApplication getAppInstance() {
        return myApplication;
    }

}
