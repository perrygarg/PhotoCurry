package com.tsystems.photocurry.home.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tsystems.photocurry.R;
import com.tsystems.photocurry.common.activity.BaseActivity;

public class HomeActivity extends BaseActivity {

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
    }

    /**
     * Method to configure toolbar for current activity
     */
    private void configureToolbar() {
        setupToolbar(getString(R.string.app_name), false);
        setToolbarBackgroundColor();
    }
}
