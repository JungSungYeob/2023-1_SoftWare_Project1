package com.example.proj2_and_2019202021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STRORAGE =1;
    private static String[] PERMISSIONS_STORAGE ={
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    //스플래쉬 뷰
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //GalleryActivity로 intent 후 start, splash view 종료
                Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);//1초 딜레이
    }
}