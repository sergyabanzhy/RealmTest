package com.example.sergzhy.realmtest;

public interface IPresenter<V> {
    void attach(V view);
    void detach();
}
