package it.ennova.photo.lib;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

class PermissionManager {

    private static final String[] PERMISSIONS = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int PERMISSION_REQUEST = 100;

    static boolean hasPermission(@NonNull AppCompatActivity target) {
        return ContextCompat.checkSelfPermission(target, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    static void requestPermission(@NonNull AppCompatActivity target, @NonNull PhotoLib photoLib) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(target, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            photoLib.onPermissionRationaleRequested();
        } else {
            ActivityCompat.requestPermissions(target, PERMISSIONS, PERMISSION_REQUEST);
        }
    }

    static void onRequestPermissionResult(int requestCode, int[] grantResults, @NonNull PhotoLib photoLib) {
        if (requestCode != PERMISSION_REQUEST) {
            return;
        }

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            photoLib.onPermissionGranted();
        }
    }
}
