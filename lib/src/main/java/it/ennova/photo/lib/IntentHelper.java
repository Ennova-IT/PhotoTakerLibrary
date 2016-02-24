package it.ennova.photo.lib;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;

class IntentHelper {

    static void checkFullSizePhotoRequest(@NonNull Intent pictureIntent,
                                          @Nullable PhotoLib listener,
                                          @NonNull String customDirectoryName) {

        if (listener != null) {
            patchIntentForFullSizePhoto(pictureIntent, listener, customDirectoryName);
        }
    }

    private static void patchIntentForFullSizePhoto(@NonNull Intent pictureIntent,
                                                    @NonNull PhotoLib listener,
                                                    @NonNull String customDirectoryName) {

        try {
            File bitmapFile = FileFactory.createImageFileWith(customDirectoryName);
            listener.onPhotoPathCreated(bitmapFile.getAbsolutePath());
            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(bitmapFile));
        } catch (Exception e) {
            listener.onPhotoPathError();
        }
    }
}
