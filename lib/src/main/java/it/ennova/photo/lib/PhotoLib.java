package it.ennova.photo.lib;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class PhotoLib {
    private static final int PHOTO_REQUEST_CODE = 100;
    @NonNull
    private AppCompatActivity targetActivity;
    @NonNull
    private String path;
    @NonNull
    private String applicationId;
    @NonNull
    private OnPhotoRetrievedListener listener;

    public PhotoLib(@NonNull AppCompatActivity targetActivity,
                    @NonNull String applicationId,
                    @NonNull OnPhotoRetrievedListener listener) {

        this.targetActivity = targetActivity;
        this.applicationId = applicationId;
        this.listener = listener;
    }

    void onPhotoPathCreated(@NonNull String path) {
        this.path = path;
    }

    void onPhotoPathError() {
        path = "";
    }

    public void takePictureFromCamera() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        pictureIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        if (pictureIntent.resolveActivity(targetActivity.getPackageManager()) != null) {
            IntentHelper.checkFullSizePhotoRequest(targetActivity, pictureIntent, this, applicationId);
            targetActivity.startActivityForResult(pictureIntent, PHOTO_REQUEST_CODE);
        }else{
            Toast.makeText(targetActivity, "No app found to take a picture", Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            PictureDecodeUtils.parsePictureFrom(data, listener, path);
        }
    }

    public String getPhotoPath() {
        return path;
    }
}
