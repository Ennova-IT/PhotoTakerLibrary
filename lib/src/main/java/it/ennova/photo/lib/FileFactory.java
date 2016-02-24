package it.ennova.photo.lib;

import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

class FileFactory {

    static File createImageFileWith(@NonNull String customDirectoryName) throws IOException {
        final String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        final String imageFileName = "JPEG_" + timestamp;
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), customDirectoryName);
        storageDir.mkdirs();
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }
}
