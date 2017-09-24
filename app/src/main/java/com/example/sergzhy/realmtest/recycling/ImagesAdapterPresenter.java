package com.example.sergzhy.realmtest.recycling;

import android.util.Log;
import android.view.View;

import com.example.sergzhy.realmtest.IPresenter;
import com.example.sergzhy.realmtest.entity.ImageEntity;
import com.example.sergzhy.realmtest.repository.ImagesLocalRepository;
import com.example.sergzhy.realmtest.repository.interactions.IAdapterInteractor;

class ImagesAdapterPresenter implements IPresenter<IAdapterView>, IAdapterCallbacks {
    private static final String TAG = ImagesAdapterPresenter.class.getName();

    private IAdapterInteractor<ImageEntity> mImagesRepository;
    private IAdapterView mAdapterView;

    int getItemsCount() {
        Log.d(TAG, "getItemsCount, + " + mImagesRepository.getItemsCount() + "");
        return mImagesRepository.getItemsCount();
    }

    void onConfigureRow(final int position, ViewHolder holder) {
        Log.d(TAG, "onConfigureRow, + " + position + "");
        final ImageEntity imageEntity = mImagesRepository.getItem(position);
        holder.setImageUri(imageEntity.getUri());
        holder.setImage(imageEntity.getBytes());
        holder.setDeleteButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImagesRepository.deleteItem(position);
            }
        });
    }

    @Override
    public void attach(IAdapterView view) {
        Log.d(TAG, "attach()");
        mAdapterView = view;
        mImagesRepository = ImagesLocalRepository.getImageRepository();
        mImagesRepository.init();
        mImagesRepository.setRepositoryCallbacks(this);
    }

    @Override
    public void detach() {
        Log.d(TAG, "detach()");
        mAdapterView = null;
        mImagesRepository.deinit();
        mImagesRepository = null;
    }

    @Override
    public void onItemAdded(int position) {
        Log.d(TAG, "onItemAdded()");
        mAdapterView.onItemAdded(position);
    }

    @Override
    public void onItemRemoved(int position) {
        Log.d(TAG, "onItemRemoved()");
        mAdapterView.onItemRemoved(position);
    }
}
