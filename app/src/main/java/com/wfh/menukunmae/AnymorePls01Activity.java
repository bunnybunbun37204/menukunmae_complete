package com.wfh.menukunmae;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class AnymorePls01Activity extends AppCompatActivity {
    private View decorView;
    private Dialog myDialog;
    private final int STATUS = Main4Activity.getStatus();
    private List<String> cooking_vol_string, cooking_method_string = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leftanymorepls);
        initialize();
        Log.i("LOG-INFO","STATUS + "+STATUS+" VOL + "+ cooking_vol_string);
        //ไฟล์นี้ไว้ปิด Navigator bar
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(Visibility -> {
            if (Visibility == 0)
                decorView.setSystemUiVisibility(hideSystemBars());

        });
    }

    private void initialize() {

        if(STATUS == 0) {
            cooking_vol_string = Main4Activity.getRandom_food().getFood_vol();
            cooking_method_string = Main4Activity.getRandom_food().getCooking_method();
        }
        else if(STATUS == 1) {
            cooking_vol_string = Main4Activity.getRandom_food_all().getFood_vol();
            cooking_method_string = Main4Activity.getRandom_food().getCooking_method();
        }

        setVolInit();
        setCookingMethodInit();
    }

    private void setVolInit(){
        ListView volListView = findViewById(R.id.list_cooking_vol);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.custom_list_item_ingredients, R.id.list_ing_component, cooking_vol_string);
        volListView.setAdapter(adapter);
    }

    private void setCookingMethodInit() {
        ListView cookingMethodListView = findViewById(R.id.cooking_method);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.custom_list_item_ingredients, R.id.list_ing_component, cooking_method_string);
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