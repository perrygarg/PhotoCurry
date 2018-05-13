package com.tsystems.photocurry.viewimage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tsystems.photocurry.R;
import com.tsystems.photocurry.common.activity.BaseActivity;
import com.tsystems.photocurry.common.constants.AppConstants;
import com.tsystems.photocurry.common.util.AppUtil;
import com.tsystems.photocurry.common.util.UIUtil;

public class ViewImageActivity extends BaseActivity {
    private ImageView imageView, mediumSizeImageView = null;
    private View progressView = null;
    private RelativeLayout parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        init();
    }

    /**
     * Method for initiating activity components. Should be called from onCreate() of current activity
     */
    private void init() {
        findViewsByIds();

        Intent intent = getIntent();
        if(intent != null) {
            String imageUrl = getIntent().getStringExtra(AppConstants.IMAGE_URL);
            String mediumUrl = getIntent().getStringExtra(AppConstants.IMAGE_URL_MEDIUM);
            if(!AppUtil.isStringEmpty(imageUrl) && !AppUtil.isStringEmpty(mediumUrl)) {
                loadImage(imageUrl, mediumUrl);
            }
        }

    }

    //Dev comment: Shared Element transition will occur only once image will be in cache. Alternatively we can put up a temporary image view with low res image till the time high res image loads up...
    //...Shared element transition will be carried out by low res image view. Once high res image view will be loaded, we will hide low res image view and bind shared element transition to the high res image view in order to shared element transition to occur while going back to first activity.
    private void loadImage(String imageUrl, String mediumUrl) {
        Glide.with(this).load(imageUrl)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(requestListener)
                .into(imageView);

        //Temporary low res image view till actual image view loads up.
        /*Glide.with(this).load(mediumUrl)
                .thumbnail(0.0f)
                .crossFade()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mediumSizeImageView);*/

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mediumSizeImageView.setVisibility(View.GONE);
            }
        }, 3000);*/
    }

    private void findViewsByIds() {
        imageView = findViewById(R.id.full_size_image_view);
        parentView = findViewById(R.id.parent_view_2);
//        mediumSizeImageView = findViewById(R.id.medium_size_image_view);
        progressView = findViewById(R.id.progress_view);
    }

    /**
     * Inner class for getting callback from Glide.
     */
    RequestListener<String, GlideDrawable> requestListener = new RequestListener() {
        @Override
        public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
            progressView.setVisibility(View.GONE);
            UIUtil.showSnackbar(parentView, getString(R.string.error_network), false);
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
//            mediumSizeImageView.setVisibility(View.GONE);
//            imageView.setTransitionName("photocurrytransition");
//            mediumSizeImageView.setTransitionName("");
            progressView.setVisibility(View.GONE);
            return false;
        }
    };

}
