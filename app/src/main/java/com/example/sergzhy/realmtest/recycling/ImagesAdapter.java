package com.example.sergzhy.realmtest.recycling;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.sergzhy.realmtest.R;

public class ImagesAdapter extends RecyclerView.Adapter<ViewHolder> implements IAdapterView {
    private static final String TAG = ImagesAdapter.class.getName();
    private ImagesAdapterPresenter mImagesAdapterPresenter;

    public ImagesAdapter() {
        Log.d(TAG, "ImagesAdapter");
        mImagesAdapterPresenter = new ImagesAdapterPresenter();
        mImagesAdapterPresenter.attach(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_layout, parent, false), parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder");
        mImagesAdapterPresenter.onConfigureRow(position, holder);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount, " + mImagesAdapterPresenter.getItemsCount() + "");
        return mImagesAdapterPresenter.getItemsCount();
    }

    @Override
    public void onItemAdded(int position) {
        Log.d(TAG, "onItemAdded");
        notifyItemInserted(position);
        notifyItemRangeChanged(position, getItemCount());
    }


    @Override
    public void onItemRemoved(int position) {
        Log.d(TAG, "onItemRemoved");
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}
