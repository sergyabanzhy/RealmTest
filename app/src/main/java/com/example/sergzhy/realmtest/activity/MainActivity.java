package com.example.sergzhy.realmtest.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sergzhy.realmtest.R;
import com.example.sergzhy.realmtest.entity.ImageEntity;
import com.example.sergzhy.realmtest.recycling.ImagesAdapter;
import com.example.sergzhy.realmtest.utils.Utils;

import java.io.IOException;

import static com.example.sergzhy.realmtest.utils.Utils.CAMERA;
import static com.example.sergzhy.realmtest.utils.Utils.CAMERA_FILE;
import static com.example.sergzhy.realmtest.utils.Utils.GALLERY;
import static com.example.sergzhy.realmtest.utils.Utils.bitmapToBytes;
import static com.example.sergzhy.realmtest.utils.Utils.showPictureDialog;

public class MainActivity extends AppCompatActivity implements IMainActivityView {
    private static final String TAG = MainActivity.class.getName();

    private RecyclerView.Adapter mPhotoAdapter;
    private RecyclerView mRecyclerView;
    private ImagePresenter mPhotoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button addButton = (Button) findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog(v.getContext());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
        mPhotoPresenter = new ImagePresenter();
        mPhotoAdapter = new ImagesAdapter();
        mRecyclerView.setAdapter(mPhotoAdapter);
        mPhotoPresenter.attach(this);


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
        mPhotoPresenter.detach();
        mPhotoPresenter = null;
        mPhotoAdapter = null;
        mRecyclerView = null;

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"onActivityResult");

        if (requestCode == GALLERY && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            Log.d(TAG, String.valueOf(uri));
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageEntity photoEntity = new ImageEntity(String.valueOf(uri), bitmapToBytes(bitmap));
            mPhotoPresenter.addImage(photoEntity);

        } else if (requestCode == CAMERA) {
            String uri = Utils.getPathFromURI(CAMERA_FILE, this);
            Log.d(TAG, String.valueOf(uri));
            Bitmap bitmap = BitmapFactory.decodeFile(uri);
            ImageEntity photoEntity = new ImageEntity(String.valueOf(uri), bitmapToBytes(bitmap));
            mPhotoPresenter.addImage(photoEntity);
        }
    }

    @Override
    public void onError(String message) {
        Log.d(TAG,"onError");
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemAdded() {
        Log.d(TAG,"onItemAdded");
        Toast.makeText(getApplicationContext(), "Item added successfully", Toast.LENGTH_SHORT).show();
    }
}

