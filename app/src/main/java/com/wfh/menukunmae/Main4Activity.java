package com.wfh.menukunmae;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Dialog;

import com.wfh.menukunmae.classes.Food;
import com.wfh.menukunmae.tools.LoadImage;

import java.util.List;

public class Main4Activity extends AppCompatActivity {

    private View decorView;
    private List<Food> foods;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main4);
        super.onCreate(savedInstanceState);

        foods = MainActivity.getFoods();

        Log.i("LOG-INFO", "TEST : " + foods.get(0).getFood_name());

        myDialog = new Dialog(this);
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(Visibility -> {
            if (Visibility == 0)
                decorView.setSystemUiVisibility(hideSystemBars());

        });
        Log.d("LOG-DEBUGGER", "CREATE");
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

    public void ShowPopup(View view) {
        final String URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Prayuth_2018_cropped.jpg/220px-Prayuth_2018_cropped.jpg";
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.custom_dialog);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        btnFollow = (Button) myDialog.findViewById(R.id.btnFollow);
        showImageViaLink(URL);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    private void showImageViaLink(String URL) {
        ImageView foodImage;
        foodImage = (ImageView) myDialog.findViewById(R.id.foodImage);
        LoadImage loadImage = new LoadImage(foodImage);
        loadImage.execute(URL);
    }
}
