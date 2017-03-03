package it.ennova.photo.lib;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;

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

            File bitmapFile = FileFactory.createImageFileWith(context);
            listener.onPhotoPathCreated(bitmapFile.getAbsolutePath());

            Uri photoURI = FileProvider.getUriForFile(context,
                    applicationId + ".provider",
                    bitmapFile);

            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                pictureIntent.setClipData(ClipData.newRawUri("", photoURI));
                pictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } catch (Exception e) {
            Log.e("IntentHelper", e.getMessage());
            listener.onPhotoPathError();
        }
    }
}
