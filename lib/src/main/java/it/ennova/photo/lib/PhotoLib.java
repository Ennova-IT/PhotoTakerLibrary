package it.ennova.photo.lib;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class PhotoLib {
    private static final int PHOTO_REQUEST_CODE = 100;

    public interface OnPhotoRetrievedListener {
        void onPhotoRetrieved(@Nullable Bitmap picture);
    }

    public interface OnPhotoPathCreatedListener {
        void onPhotoPathCreated(@NonNull String path);
        void onPhotoPathError();
    }

    public static void takePictureFromCameraWith(@NonNull Activity activity,
                                                 @Nullable OnPhotoPathCreatedListener listener,
                                                 @NonNull String customDirectoryName) {

        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            IntentHelper.checkFullSizePhotoRequest(pictureIntent, listener, customDirectoryName);
            activity.startActivityForResult(pictureIntent, PHOTO_REQUEST_CODE);
        }
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data,
                                        @NonNull OnPhotoRetrievedListener listener,  @NonNull String path) {

        if (requestCode == PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            PictureDecodeUtils.parsePictureFrom(data, listener, path);
        }
    }


}
