package com.example.sergzhy.realmtest.recycling;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sergzhy.realmtest.R;

class ViewHolder extends RecyclerView.ViewHolder implements IImageViewRow {
    private static final String TAG = ViewHolder.class.getName();

    private TextView mTextView;
    private ImageView mImageView;
    private Button mDeleteImageButton;
    private Context mContext;

    ViewHolder(View itemView, Context context) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.text);
        mImageView = (ImageView) itemView.findViewById(R.id.image);
        mDeleteImageButton = (Button) itemView.findViewById(R.id.delete_button);
        this.mContext = context;
    }

    @Override
    public void setImageUri(String uri) {
        Log.d(TAG, "setImageUri, " + uri + "");
        mTextView.setText(uri);
    }

    @Override
    public void setImage(byte[] bytes) {
        Log.d(TAG, "setImage");
        Glide.with(mContext).load(bytes).centerCrop().into(mImageView);
    }

    @Override
    public void setDeleteButtonClickListener(View.OnClickListener onClickListener) {
        Log.d(TAG, "setDeleteButtonClickListener");
        mDeleteImageButton.setOnClickListener(onClickListener);
    }

}
