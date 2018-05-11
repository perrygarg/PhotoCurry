package com.tsystems.photocurry.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.tsystems.photocurry.R;
import com.tsystems.photocurry.application.MyApplication;

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private BaseRecyclerAdapterListener baseRecyclerAdapterListener =  null;
    private LayoutInflater layoutInflater = null;

    private int total = 0;
    private int count = 0;
    private boolean isLoadMore = true;
    private boolean isPagination = true;

    private final int ITEM_PROGRESS_SHOWN = 100;
    private final int ITEM_ERROR_SHOWN = 101;

    protected BaseRecyclerAdapter(BaseRecyclerAdapterListener baseRecyclerAdapterListener, boolean isPagination)
    {
        layoutInflater = (LayoutInflater) MyApplication.appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.baseRecyclerAdapterListener = baseRecyclerAdapterListener;
        this.isPagination = isPagination;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case ITEM_PROGRESS_SHOWN:

                View view = layoutInflater.inflate(R.layout.progress_layout, parent, false);
                viewHolder = new ViewHolderProgressItem(view);
                view.setVisibility(View.GONE);

                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        int viewType = getItemViewType(position);

        switch (viewType)
        {
            case ITEM_PROGRESS_SHOWN:
                displayProgress(holder, position);
                break;
        }

    }

    private void displayProgress(RecyclerView.ViewHolder holder, int position)
    {
//        if(isLoadMore) {
            if (count < total) {

                ViewHolderProgressItem viewHolderProgressItem = (ViewHolderProgressItem) holder;

                if (position < total)
                {
//                    if(isLoadMore) {
                        viewHolderProgressItem.relativeLayoutBottom.setVisibility(View.VISIBLE);

                        if (baseRecyclerAdapterListener != null) {
                            baseRecyclerAdapterListener.loadMore();
                        }
//                    }
                    /*else
                    {
                        viewHolderProgressItem.relativeLayoutBottom.setVisibility(View.INVISIBLE);

                        if (baseRecyclerAdapterListener != null) {
                            baseRecyclerAdapterListener.handleError();
                        }
                    }*/
                }
                else
                {
                    viewHolderProgressItem.relativeLayoutBottom.setVisibility(View.INVISIBLE);
                }
            }
//        }
//        else
//        {
//            displayError();
//        }
    }

//    private void displayError()
//    {
//        if(baseRecyclerAdapterListener != null)
//        {
//            baseRecyclerAdapterListener.handleError();
//        }
//    }

    private static class ViewHolderProgressItem extends RecyclerView.ViewHolder
    {
        RelativeLayout relativeLayoutBottom = null;
        ProgressBar progressBar = null;

        public ViewHolderProgressItem(View itemView)
        {
            super(itemView);

            relativeLayoutBottom = itemView.findViewById(R.id.relativeLayoutBottom);
            progressBar = itemView.findViewById(R.id.imageViewProgress);
        }
    }

    @Override
    public int getItemCount()
    {
        count = getCount();
        return count;
    }

    @Override
    public int getItemViewType(int position) {

        if((position + 1) == count && isPagination)
        {
            return ITEM_PROGRESS_SHOWN;
        }

        return getViewType(position);
    }

    public void setLoadMore(boolean isLoadMore)
    {
        this.isLoadMore = isLoadMore;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    protected abstract int getCount();

    protected abstract int getViewType(int position);

//    protected abstract RecyclerView.ViewHolder onCreateViewHolder();
}

