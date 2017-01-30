package it.ennova.photo.lib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;

import java.io.File;

class IntentHelper {

    static void checkFullSizePhotoRequest(@NonNull Context context,
                                          @NonNull Intent pictureIntent,
                                          @Nullable PhotoLib listener,
                                          @NonNull String applicationId) {

        if (listener != null) {
            patchIntentForFullSizePhoto(context, pictureIntent, listener, applicationId);
        }
    }

    private static void patchIntentForFullSizePhoto(@NonNull Context context,
                                                    @NonNull Intent pictureIntent,
                                                    @NonNull PhotoLib listener,
                                                    @NonNull String applicationId) {

        try {

            File bitmapFile = FileFactory.createImageFileWith();
            listener.onPhotoPathCreated(bitmapFile.getAbsolutePath());

            Uri photoURI = FileProvider.getUriForFile(context,
                    applicationId + ".provider",
                    bitmapFile);

            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        } catch (Exception e) {
            listener.onPhotoPathError();
        }
    }
}
