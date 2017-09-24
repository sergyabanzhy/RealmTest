package com.example.sergzhy.realmtest.repository.interactions;

public interface IAdapterInteractor<E> extends IBaseInteractor {
    E getItem(int position);
    int getItemsCount();
    void deleteItem(int position);
}
