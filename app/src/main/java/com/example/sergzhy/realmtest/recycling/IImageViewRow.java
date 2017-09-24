package com.example.sergzhy.realmtest.recycling;
import android.view.View;

interface IImageViewRow {
    void setImageUri(String uri);
    void setImage(byte[] bytes);
    void setDeleteButtonClickListener(View.OnClickListener onClickListener);

}
