package it.ennova.photo.lib;

import android.annotation.SuppressLint;
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
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    static Bitmap decodeCorrectlyOrientedImageFrom(@NonNull String path) {
        Bitmap source = BitmapFactory.decodeFile(path, provideCompressionBitmapFactoryOptions());

        try {
            source = BitmapUtils.transform(path, source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return source;
    }

    private static BitmapFactory.Options provideCompressionBitmapFactoryOptions() {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = false;
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        return opt;
    }
}
