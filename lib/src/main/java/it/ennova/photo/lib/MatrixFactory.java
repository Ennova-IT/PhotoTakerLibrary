package it.ennova.photo.lib;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.annotation.NonNull;

class MatrixFactory {

    static Matrix buildRotationMatrixFrom(int orientation) {
        Matrix rotationMatrix = new Matrix();
        rotationMatrix.postRotate(getAngleFrom(orientation));
        return rotationMatrix;
    }

    private static float getAngleFrom(final int orientation) {
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return 90;
            case ExifInterface.ORIENTATION_ROTATE_180:
                return 180;
            case ExifInterface.ORIENTATION_ROTATE_270:
                return 270;
            default:
                return 0;
        }
    }

    static Matrix buildHorizontalFlipMatrixFrom(@NonNull Bitmap source) {
        Matrix flipHorizontalMatrix = new Matrix();
        flipHorizontalMatrix.setScale(-1,1);
        flipHorizontalMatrix.postTranslate(source.getWidth(),0);
        return flipHorizontalMatrix;
    }

    static Matrix buildVerticalFlipMatrixFrom(@NonNull Bitmap source) {
        Matrix flipVerticalMatrix = new Matrix();
        flipVerticalMatrix.setScale(1,-1);
        flipVerticalMatrix.postTranslate(0,source.getHeight());
        return flipVerticalMatrix;
    }

    static Matrix buildDiagonalFlipMatrixFrom(@NonNull Bitmap source) {
        Matrix flipDiagonalMatrix = new Matrix();
        flipDiagonalMatrix.setScale(1,-1);
        flipDiagonalMatrix.postTranslate(0,source.getHeight());
        return flipDiagonalMatrix;
    }
}
