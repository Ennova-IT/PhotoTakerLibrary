package it.ennova.photo.lib;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

class PictureDecodeUtils {

    static void parsePictureFrom(@NonNull Intent data, @NonNull OnPhotoRetrievedListener listener, @NonNull String path) {
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
        listener.onPhotoRetrieved(FileFactory.decodeCorrectlyOrientedImageFrom(path));
    }
}
