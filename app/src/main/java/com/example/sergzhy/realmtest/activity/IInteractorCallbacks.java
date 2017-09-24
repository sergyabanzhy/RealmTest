package com.example.sergzhy.realmtest.activity;

import com.example.sergzhy.realmtest.repository.IRepositoryCallbacks;

public interface IInteractorCallbacks extends IRepositoryCallbacks {
    void onError(String message);
    void onItemAdded();
}
