package com.example.sergzhy.realmtest.repository.interactions;


import com.example.sergzhy.realmtest.repository.IRepositoryCallbacks;

public interface IBaseInteractor {
    void init();
    void deinit();
    void setRepositoryCallbacks(IRepositoryCallbacks repositoryCallbacks);
}
