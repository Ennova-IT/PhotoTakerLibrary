package it.ennova.photo.lib;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class PhotoLib {
    private static final int PHOTO_REQUEST_CODE = 100;

    public interface OnPhotoRetrievedListener {
        void onPhotoRetrieved(@Nullable Bitmap picture);
    }

    public static void takePictureFromCameraWith(@NonNull Activity activity) {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(pictureIntent, PHOTO_REQUEST_CODE);
        }
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data, @NonNull OnPhotoRetrievedListener listener) {
        if (requestCode == PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            parsePictureFrom(data, listener);
        }
    }

    private static void parsePictureFrom(@NonNull Intent data, @NonNull OnPhotoRetrievedListener listener) {
        Bundle extras = data.getExtras();
        if (extras != null && extras.containsKey("data")) {
            listener.onPhotoRetrieved((Bitmap) extras.get("data"));
        }
    }
}
