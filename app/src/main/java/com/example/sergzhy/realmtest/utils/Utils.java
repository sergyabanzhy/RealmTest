package com.example.sergzhy.realmtest.utils;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.sergzhy.realmtest.R;
import com.example.sergzhy.realmtest.activity.MainActivity;
import com.example.sergzhy.realmtest.entity.ImageEntity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class Utils {
    private static final String TAG = MainActivity.class.getName();

    public static int GALLERY = 1, CAMERA = 2;
    public static Uri CAMERA_FILE;

    public static byte[] bitmapToBytes(Bitmap bitmap) {
        Log.d(TAG, "bitmapToBytes");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
        return stream.toByteArray();
    }

    public static void showPictureDialog(final Context context){
        Log.d(TAG, "showPictureDialog");
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(context);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery((Activity) context);
                                break;
                            case 1:
                                takePhotoFromCamera((Activity) context);
                                break;
                        }
                    }
                });

        pictureDialog.show();
    }

    private static void choosePhotoFromGallery(Activity activity) {
        Log.d(TAG, "choosePhotoFromGallery");
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        activity.startActivityForResult(galleryIntent, GALLERY);
    }

    private static void takePhotoFromCamera(Activity activity) {
        Log.d(TAG, "takePhotoFromCamera");
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        CAMERA_FILE = activity.getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, CAMERA_FILE);
        activity.startActivityForResult(intent, CAMERA);
    }

    public static String getPathFromURI(Uri contentUri, Activity activity) {
        Log.d(TAG, "getPathFromURI");
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = activity.managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
