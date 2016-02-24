package it.ennova.photo.lib;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;

public class PhotoLib {
    private static final int PHOTO_REQUEST_CODE = 100;

    public interface OnPhotoRetrievedListener {
        void onPhotoRetrieved(@Nullable Bitmap picture);
    }

    public interface OnPhotoPathCreatedListener {
        void onPhotoPathCreated(@NonNull String path);
        void onPhotoPathError();
    }

    public static void takePictureFromCameraWith(@NonNull Activity activity, @Nullable OnPhotoPathCreatedListener listener) {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            checkFullSizeRequest(pictureIntent, listener);
            activity.startActivityForResult(pictureIntent, PHOTO_REQUEST_CODE);
        }
    }

    private static void checkFullSizeRequest(@NonNull Intent pictureIntent, @Nullable OnPhotoPathCreatedListener listener) {

        if (listener != null) {
            patchIntentForFullSizePhoto(pictureIntent, listener);
        }
    }

    private static void patchIntentForFullSizePhoto(@NonNull Intent pictureIntent, @NonNull OnPhotoPathCreatedListener listener) {

        try {
            File bitmapFile = FileFactory.createImageFileWith();
            listener.onPhotoPathCreated(bitmapFile.getAbsolutePath());
            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(bitmapFile));
        } catch (Exception e) {
             listener.onPhotoPathError();
        }
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data,
                                        @NonNull OnPhotoRetrievedListener listener,  @NonNull String path) {

        if (requestCode == PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            parsePictureFrom(data, listener, path);
        }
    }

    private static void parsePictureFrom(@NonNull Intent data, @NonNull OnPhotoRetrievedListener listener, @NonNull String path) {
        if (TextUtils.isEmpty(path)) {
            extractThumbnailFrom(data, listener);
        } else {
            decodePictureFrom(path, listener);
        }
    }

    private static void extractThumbnailFrom(@NonNull Intent data, @NonNull OnPhotoRetrievedListener listener) {
        Bundle extras = data.getExtras();
        if (extras != null && extras.containsKey("data")) {
            listener.onPhotoRetrieved((Bitmap) extras.get("data"));
        }
    }

    private static void decodePictureFrom(@NonNull String path, @NonNull OnPhotoRetrievedListener listener) {
        listener.onPhotoRetrieved(BitmapFactory.decodeFile(path));
    }
}
