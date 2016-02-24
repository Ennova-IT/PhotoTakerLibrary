package it.ennova.photo.lib;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.annotation.NonNull;

import java.io.IOException;

class BitmapUtils {

    static Bitmap transform(@NonNull String path, @NonNull Bitmap bitmap) throws IOException {

        ExifInterface exif = new ExifInterface(path);
        final int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        Matrix matrix;
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
            case ExifInterface.ORIENTATION_ROTATE_180:
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix = MatrixFactory.buildRotationMatrixFrom(orientation);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix = MatrixFactory.buildVerticalFlipMatrixFrom(bitmap);
                break;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix = MatrixFactory.buildHorizontalFlipMatrixFrom(bitmap);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix = MatrixFactory.buildDiagonalFlipMatrixFrom(bitmap);
                break;
            case ExifInterface.ORIENTATION_NORMAL:
            case ExifInterface.ORIENTATION_UNDEFINED:
            default:
                matrix = new Matrix();
        }

        return applyTransformationMatrix(bitmap, matrix);
    }

    private static Bitmap applyTransformationMatrix(Bitmap bitmap, Matrix transformationMatrix) {
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), transformationMatrix, true);
    }
}
