package com.example.sergzhy.realmtest.repository;


import android.util.Log;

import com.example.sergzhy.realmtest.activity.IInteractorCallbacks;
import com.example.sergzhy.realmtest.recycling.IAdapterCallbacks;
import com.example.sergzhy.realmtest.repository.interactions.IBaseInteractor;
import com.example.sergzhy.realmtest.repository.interactions.IAdapterInteractor;
import com.example.sergzhy.realmtest.repository.interactions.IRepositoryInteractor;

abstract class Repository<E> implements IBaseInteractor, IAdapterInteractor<E>, IRepositoryInteractor<E> {
    private static final String TAG = Repository.class.getName();

    IAdapterCallbacks mRecyclingAdapterCallback;
    IInteractorCallbacks mInteractorCallback;

    public void setRepositoryCallbacks(IRepositoryCallbacks repositoryCallbacks) {
        Log.d(TAG, "setRepositoryCallbacks");
        if (repositoryCallbacks instanceof IAdapterCallbacks) {
            this.mRecyclingAdapterCallback = (IAdapterCallbacks) repositoryCallbacks;
        } else if (repositoryCallbacks instanceof IInteractorCallbacks) {
            this.mInteractorCallback = (IInteractorCallbacks) repositoryCallbacks;
        }
    }
}
