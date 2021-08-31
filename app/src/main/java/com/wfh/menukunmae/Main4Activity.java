package com.wfh.menukunmae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Dialog;

import com.wfh.menukunmae.classes.Food;
import com.wfh.menukunmae.tools.LoadImage;
import com.wfh.menukunmae.tools.Utils;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    private View decorView;
    private static int status;
    private static Food random_food, random_food_all;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main4);
        super.onCreate(savedInstanceState);


        myDialog = new Dialog(this);
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

    public void ShowPopup(View view) {
        randomFood();
        if (random_food != null) {
            final String URL = random_food.getFood_img();
            TextView txtclose, textInfo;
            Button btnFollow;
            myDialog.setContentView(R.layout.custom_dialog);
            txtclose = myDialog.findViewById(R.id.txtclose);
            textInfo = myDialog.findViewById(R.id.textViewx);
            btnFollow = myDialog.findViewById(R.id.btnFollow);
            btnFollow.setOnClickListener(v -> {
                Intent intent = new Intent(this, AnymorePls01Activity.class);
                startActivity(intent);
            });
            showImageViaLink(URL);
            textInfo.setText(random_food.getFood_name());
            txtclose.setOnClickListener(v -> myDialog.dismiss());
            myDialog.show();
        } else {
            Utils.makeToast("ไม่พบรายการอาหาร", getApplicationContext());
        }
    }

    public void ShowPopupleft(View view) {
        randomAllFood();
        if (random_food_all != null) {
            final String URL = random_food_all.getFood_img();
            TextView txtclose, textInfo;
            Button btnFollow;
            myDialog.setContentView(R.layout.custom_dialogleft);
            txtclose = myDialog.findViewById(R.id.txtclose01);
            textInfo = myDialog.findViewById(R.id.textInfo);
            btnFollow = myDialog.findViewById(R.id.btnFollow01);
            btnFollow.setOnClickListener(v -> {
                Intent intent = new Intent(this, AnymorePls01Activity.class);
                startActivity(intent);
            });
            showImageViaLinkAllFood(URL);
            textInfo.setText(random_food_all.getFood_name());
            txtclose.setOnClickListener(v -> myDialog.dismiss());
            myDialog.show();
        } else {
            Utils.makeToast("ไม่พบรายการอาหาร", getApplicationContext());
        }
    }

    private void showImageViaLink(String URL) {
        ImageView foodImage;
        foodImage = myDialog.findViewById(R.id.foodImage);
        LoadImage loadImage = new LoadImage(foodImage);
        loadImage.execute(URL);
    }

    private void showImageViaLinkAllFood(String URL) {
        ImageView foodImage;
        foodImage = myDialog.findViewById(R.id.foodImage01);
        try {
            LoadImage loadImage = new LoadImage(foodImage);
            loadImage.execute(URL);
            Log.d("LOAD-IMAGE-DEBUGGER","PASS");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void randomFood() {
        List<Food> foods = MainActivity.getFoods();
        ArrayList<String> ingredients = new ArrayList<String>();

        try {
            ingredients = Main3Activity.getIngredientsList(getApplicationContext());
            Log.d("LOG-DEBUGGER", "PASS!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Food> selected_foods = Utils.searchFoodByUsingSubset(ingredients, foods);
        try {
            random_food = Utils.getFoodRandomly(selected_foods);
        } catch (Exception e) {
            e.printStackTrace();
            random_food = null;
        }
        status = 0;
    }

    private void randomAllFood() {
        List<Food> foods = MainActivity.getFoods();
        try {
            random_food_all = Utils.getFoodRandomly(foods);
        } catch (Exception e) {
            e.printStackTrace();
            random_food_all = null;
        }
        status = 1;
    }

    public static int getStatus() {
        return status;
    }

    public static Food getRandom_food() {
        return random_food;
    }

    public static Food getRandom_food_all() {
        return random_food_all;
    }
}
