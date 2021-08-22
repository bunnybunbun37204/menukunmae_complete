package com.wfh.menukunmae;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


public class Main2Activity extends AppCompatActivity {

    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //ปุ่มเข้าหน้าตู้เย็นของฉัน
        Button button = findViewById(R.id.button4);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(this, Main3Activity.class);
            startActivity(intent);
        });
        //ปุ่มเข้าหน้าปุ่มส่ม
        Button button1 = findViewById(R.id.button5);
        button1.setOnClickListener(view -> {
            Intent intent = new Intent(this, Main4Activity.class);
            startActivity(intent);
        });
        //ปุ่มเข้าหน้าคำนวณแคลอรี่
        Button button2 = findViewById(R.id.button6);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(this, Main5Activity.class);
            startActivity(intent);
        });
        //ไฟล์นี้ไว้ปิด Navigator bar
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(Visibility -> {
            if (Visibility == 0)
                decorView.setSystemUiVisibility(hideSystemBars());

        });


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
}