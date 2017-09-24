package com.example.sergzhy.realmtest.recycling;

import com.example.sergzhy.realmtest.repository.IRepositoryCallbacks;

public interface IAdapterCallbacks extends IRepositoryCallbacks {
    void onItemAdded(int position);
    void onItemRemoved(int position);
}
