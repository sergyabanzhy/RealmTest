package com.example.sergzhy.realmtest.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.sergzhy.realmtest.entity.ImageEntity;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ImagesLocalRepository extends Repository<ImageEntity> {
    private static final String TAG = ImagesLocalRepository.class.getName();
    private Realm mRealm;
    private RealmResults<ImageEntity> mRealmPhotosResults;
    private static volatile ImagesLocalRepository sImageRepository;

    /**
     *
     */
    public static ImagesLocalRepository getImageRepository() {
        if (sImageRepository == null) {
            synchronized (ImagesLocalRepository.class) {
                if (sImageRepository == null) {
                    sImageRepository = new ImagesLocalRepository();
                }
            }
        }
        return sImageRepository;
    }

    private ImagesLocalRepository() {
        Log.d(TAG,"ImagesLocalRepository");
    }

    public ImageEntity getItem(int position) {
        Log.d(TAG,"getItem");
        return mRealmPhotosResults.get(position);
    }

    public int getItemsCount() {
        Log.d(TAG,"getItem");
        return mRealmPhotosResults.size();
    }

    @Override
    public void addItem(final ImageEntity photoEntity) {

        Log.d(TAG,"addItem");

       mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm bgRealm) {
                String uri = photoEntity.getUri();
                ImageEntity imageEntity = bgRealm.createObject(ImageEntity.class, uri);
                imageEntity.setBytes(photoEntity.getBytes());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                mRecyclingAdapterCallback.onItemAdded(mRealmPhotosResults.size() - 1);
                mInteractorCallback.onItemAdded();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(@NonNull Throwable error) {
                mInteractorCallback.onError(error.getMessage());
            }
        });
    }

    @Override
    public void deleteItem(final int position) {
        Log.d(TAG,"deleteItem");
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                mRealmPhotosResults.deleteFromRealm(position);
                mRecyclingAdapterCallback.onItemRemoved(position);
            }
        });
    }

    @Override
    public void deinit() {
        Log.d(TAG,"deinit");
        mRealm.removeAllChangeListeners();
        mRealm.close();
    }

    @Override
    public void init() {
        Log.d(TAG,"init");
            mRealm =  Realm.getDefaultInstance();
            mRealmPhotosResults = mRealm.where(ImageEntity.class).findAllAsync();
            mRealmPhotosResults.addChangeListener(new RealmChangeListener<RealmResults<ImageEntity>>() {
                @Override
                public void onChange(@NonNull RealmResults<ImageEntity> photoEntities) {
                    mRealmPhotosResults = photoEntities;
                }
            });
    }
}
