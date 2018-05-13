package com.tsystems.photocurry.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tsystems.photocurry.R;
import com.tsystems.photocurry.application.MyApplication;
import com.tsystems.photocurry.common.adapter.BaseRecyclerAdapter;
import com.tsystems.photocurry.common.adapter.BaseRecyclerAdapterListener;
import com.tsystems.photocurry.common.constants.AppConstants;
import com.tsystems.photocurry.common.listeners.OnItemClickListener;
import com.tsystems.photocurry.common.util.AppLogs;
import com.tsystems.photocurry.home.model.Image;

import java.util.List;

/**
 * Created by PerryGarg on 10-05-2018.
 */

public class ImagesListAdapter extends BaseRecyclerAdapter {
    private List<Image> images;
    private Context mContext;
    private LayoutInflater layoutInflater = null;
    private static OnItemClickListener itemClickListener = null;

    public ImagesListAdapter(BaseRecyclerAdapterListener baseRecyclerAdapterListener, Context context, List<Image> images, OnItemClickListener itemClickListener) {
        super(baseRecyclerAdapterListener, true);
        mContext = context;
        this.images = images;
        layoutInflater = (LayoutInflater) MyApplication.appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            thumbnail = view.findViewById(R.id.thumbnail);
            thumbnail.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            int position = (int) view.getTag();

            if(itemClickListener != null)
                itemClickListener.onItemClick(position, view);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType)
        {
            case AppConstants.ITEM_SEARCH_IMAGE:
                View view = layoutInflater.inflate(R.layout.image_thumbnail_layout, parent, false);
                viewHolder = new MyViewHolder(view);

                break;

            default:
                viewHolder = super.onCreateViewHolder(parent, viewType);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //Dev comment: App might get crashed very intermittently while scrolling due to a known bug of Recycler View: https://issuetracker.google.com/issues/37007605#c10
        int viewType = getItemViewType(position);

        switch (viewType)
        {
            case AppConstants.ITEM_SEARCH_IMAGE:

                Image image = images.get(position);

                MyViewHolder myHolder = (MyViewHolder) holder;

                myHolder.thumbnail.setTag(position);

                Glide.with(mContext).load(image.getSmallSizeUrl())
                        .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(myHolder.thumbnail);
                break;

            default:

                super.onBindViewHolder(holder, position);
        }

    }

    @Override
    protected int getCount() {
        return images.size();
    }

    @Override
    protected int getViewType(int position) {
        return AppConstants.ITEM_SEARCH_IMAGE;
    }
}
