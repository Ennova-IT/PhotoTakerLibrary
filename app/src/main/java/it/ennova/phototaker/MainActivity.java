package it.ennova.phototaker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
        photoLib = new PhotoLib(this, BuildConfig.APPLICATION_ID, this);
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
    public void onError() {
        Toast.makeText(this, "An error occurred. Please retry", Toast.LENGTH_SHORT).show();
    }
}
