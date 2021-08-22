package com.wfh.menukunmae;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;


public class Main5Activity extends AppCompatActivity {

    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        //ไฟล์นี้ไว้ปิด Navigator bar
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(Visibility -> {
            if (Visibility == 0)
                decorView.setSystemUiVisibility(hideSystemBars());

        });
    }

    //ไฟล์นี้ไว้ปิด Navigator bar
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    //ไฟล์นี้ไว้ปิด Navigator bar
    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
}