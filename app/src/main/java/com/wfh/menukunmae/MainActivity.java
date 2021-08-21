package com.wfh.menukunmae;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.opengl.Visibility;

import com.wfh.menukunmae.classes.Food;
import com.wfh.menukunmae.tools.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "food.json");

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<Food>>() { }.getType();

        List<Food> foods = gson.fromJson(jsonFileString, listUserType);
        for (int i = 0; i < foods.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + foods.get(i));
        }

        Button button = findViewById(R.id.button3);
        button.setOnClickListener(view ->  {

            Intent intent = new Intent(this,Main2Activity.class);

            startActivity(intent);

        });
        //ไฟล์นี้ไว้ปิด Navigator bar
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(Visibility -> {
            if (Visibility == 0)
                decorView.setSystemUiVisibility(hideSystemBars());

        });
    }
    //ไฟล์นี้ไว้ปิด Navigator bar
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }
    //ไฟล์นี้ไว้ปิด Navigator bar
    private int hideSystemBars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

}