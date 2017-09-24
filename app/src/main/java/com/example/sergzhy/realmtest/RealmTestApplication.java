package com.example.sergzhy.realmtest;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmTestApplication extends Application {
    private static final String TAG = RealmTestApplication.class.getName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("photos.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}
