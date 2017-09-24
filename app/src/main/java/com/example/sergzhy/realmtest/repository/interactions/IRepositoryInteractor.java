package com.example.sergzhy.realmtest.repository.interactions;

public interface IRepositoryInteractor<E> extends IBaseInteractor{
    void addItem(E item);
}
