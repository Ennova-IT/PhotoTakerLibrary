package it.ennova.photo.lib;


import android.graphics.Bitmap;
import android.support.annotation.Nullable;

public interface OnPhotoRetrievedListener {

    void onPhotoRetrieved(@Nullable Bitmap picture);

    void onPermissionRationaleRequested();
}
