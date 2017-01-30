package it.ennova.photo.lib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

class FileFactory {

    static File createImageFileWith() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    static Bitmap decodeCorrectlyOrientedImageFrom(@NonNull String path) {
        Bitmap source = BitmapFactory.decodeFile(path);

        try {
            source = BitmapUtils.transform(path, source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return source;
    }
}
