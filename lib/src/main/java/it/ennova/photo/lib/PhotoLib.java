package it.ennova.photo.lib;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

public class PhotoLib {
    private static final int PHOTO_REQUEST_CODE = 100;
    @NonNull
    private AppCompatActivity targetActivity;
    @NonNull
    private String customDirectoryName;
    @NonNull
    private String path;
    @NonNull
    private OnPhotoRetrievedListener listener;

    public PhotoLib(@NonNull AppCompatActivity targetActivity, @NonNull String customDirectoryName,
                    @NonNull OnPhotoRetrievedListener listener) {

        this.targetActivity = targetActivity;
        this.customDirectoryName = customDirectoryName;
        this.listener = listener;
    }

    void onPhotoPathCreated(@NonNull String path) {
        this.path = path;
    }

    void onPhotoPathError() {
        path = "";
    }

    public void takePictureFromCamera() {

        if (PermissionManager.hasPermission(targetActivity)) {
            takePicture();
        } else {
            PermissionManager.requestPermission(targetActivity, this);
        }
    }

    private void takePicture() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(targetActivity.getPackageManager()) != null) {
            IntentHelper.checkFullSizePhotoRequest(pictureIntent, this, customDirectoryName);
            targetActivity.startActivityForResult(pictureIntent, PHOTO_REQUEST_CODE);
        }
    }

    void onPermissionGranted() {
        takePicture();
    }

    void onPermissionRationaleRequested() {
        listener.onPermissionRationaleRequested();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            PictureDecodeUtils.parsePictureFrom(data, listener, path);
        }
    }

    public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResults) {
        PermissionManager.onRequestPermissionResult(requestCode, grantResults, this);
    }
}
