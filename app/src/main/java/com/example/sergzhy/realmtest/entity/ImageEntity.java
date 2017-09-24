package com.example.sergzhy.realmtest.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ImageEntity extends RealmObject {

    public ImageEntity(String mUri, byte[] mBytes) {
        this.mUri = mUri;
        this.mBytes = mBytes;
    }

    public ImageEntity() {

    }

    @PrimaryKey
    private String mUri;
    private byte [] mBytes;

    public byte[] getBytes() {
        return mBytes;
    }

    public void setBytes(byte[] bytes) {
        this.mBytes = bytes;
    }

    public String getUri() {
        return mUri;
    }
}
