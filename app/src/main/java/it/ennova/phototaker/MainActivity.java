package it.ennova.phototaker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import it.ennova.photo.lib.PhotoLib;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        PhotoLib.OnPhotoRetrievedListener, PhotoLib.OnPhotoPathCreatedListener {

    private ImageView photoView;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.takePhotoButton).setOnClickListener(this);
        photoView = (ImageView) findViewById(R.id.photo);
    }

    @Override
    public void onClick(View v) {
        PhotoLib.takePictureFromCameraWith(this, this);
    }

    @Override
    public void onPhotoPathCreated(@NonNull String path) {
        imagePath = path;
    }

    @Override
    public void onPhotoPathError() {
        imagePath = "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoLib.onActivityResult(requestCode, resultCode, data, this, imagePath);
    }

    @Override
    public void onPhotoRetrieved(@Nullable Bitmap picture) {
        photoView.setImageBitmap(picture);
    }
}
