package com.tsystems.photocurry.home.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.tsystems.photocurry.R;
import com.tsystems.photocurry.common.activity.BaseActivity;

public class HomeActivity extends BaseActivity {
    private String TAG = HomeActivity.class.getSimpleName();
    private RecyclerView imagesRecView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    /**
     * Method for initiating activity components. Should be called from onCreate() of current activity
     */
    private void init() {
        configureToolbar();

        findViewsByIds();
    }

    /**
     * Method responsible for importing all views into java code for this activity
     * Should be called from {@link #init()}
     */
    private void findViewsByIds() {
        imagesRecView = findViewById(R.id.images_rec_view);
    }

    /**
     * Method to configure toolbar for current activity
     */
    private void configureToolbar() {
        setupToolbar(getString(R.string.app_name), false);
        setToolbarBackgroundColor();
    }
}
