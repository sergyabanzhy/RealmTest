package com.example.sergzhy.realmtest.activity;

import android.util.Log;

import com.example.sergzhy.realmtest.IPresenter;
import com.example.sergzhy.realmtest.entity.ImageEntity;
import com.example.sergzhy.realmtest.repository.interactions.IRepositoryInteractor;
import com.example.sergzhy.realmtest.repository.ImagesLocalRepository;


class ImagePresenter implements IPresenter<IMainActivityView>, IInteractorCallbacks {
    private static final String TAG = MainActivity.class.getName();

    private IRepositoryInteractor<ImageEntity> mImagesRepositoryInteractor;
    private IMainActivityView mMainActivityView;
    @Override
    public void attach(IMainActivityView view) {
        Log.d(TAG, "attach()");
        this.mImagesRepositoryInteractor = ImagesLocalRepository.getImageRepository();
        this.mMainActivityView = view;
        mImagesRepositoryInteractor.setRepositoryCallbacks(this);
    }

    @Override
    public void detach() {
        Log.d(TAG, "detach()");
        mImagesRepositoryInteractor.deinit();
        mImagesRepositoryInteractor = null;
    }

     void addImage(ImageEntity photoEntity) {
        Log.d(TAG, "addItem()");
        mImagesRepositoryInteractor.addItem(photoEntity);
    }

    @Override
    public void onError(String message) {
        Log.d(TAG, "onError()");
        mMainActivityView.onError(message);
    }

    @Override
    public void onItemAdded() {
        Log.d(TAG, "onItemAdded()");
        mMainActivityView.onItemAdded();
    }
}
