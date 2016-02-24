package it.ennova.phototaker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import it.ennova.photo.lib.OnPhotoRetrievedListener;
import it.ennova.photo.lib.PhotoLib;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnPhotoRetrievedListener {

    private ImageView photoView;
    private PhotoLib photoLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.takePhotoButton).setOnClickListener(this);
        photoView = (ImageView) findViewById(R.id.photo);
        photoLib = new PhotoLib(this, "Your App Name Here", this);
    }

    @Override
    public void onClick(View v) {
        photoLib.takePictureFromCamera();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photoLib.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPhotoRetrieved(@Nullable Bitmap picture) {
        photoView.setImageBitmap(picture);
    }

    @Override
    public void onPermissionRationaleRequested() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        photoLib.onRequestPermissionResult(requestCode, permissions, grantResults);
    }
}
