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
    private Food random_food, random_food01;
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
        if(random_food != null){
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
            showImageViaLinkRandomDialog(URL);
            textInfo.setText(random_food.getFood_name());
            txtclose.setOnClickListener(v -> myDialog.dismiss());
            myDialog.show();
        }
        else {
            Utils.makeToast("ไม่พบรายการอาหาร",getApplicationContext());
        }

    }

    public void ShowPopupleft(View view) {
        randomAllFood();
        if(random_food != null){
            final String URL = random_food.getFood_img();
            TextView txtclose01, textInfo;
            Button btnFollow01;
            myDialog.setContentView(R.layout.custom_dialogleft);
            txtclose01 = (TextView) myDialog.findViewById(R.id.txtclose01);
            btnFollow01 = (Button) myDialog.findViewById(R.id.btnFollow01);
            textInfo = (TextView) myDialog.findViewById(R.id.textInfo);
            txtclose01.setOnClickListener(v -> myDialog.dismiss());
            showImageViaLinkDialog(URL);
            textInfo.setText(random_food.getFood_name());
            myDialog.show();
        }

        else {
            Utils.makeToast("ไม่พบรายการอาหาร",getApplicationContext());
        }

    }

    private void showImageViaLinkRandomDialog(String URL) {
        ImageView imageView = myDialog.findViewById(R.id.foodImage);
        LoadImage loadImage = new LoadImage(imageView);
        try {
            loadImage.execute(URL);
            Log.d("LOG-DEBUGGER","MSG : SUCKZZEED");
        }
        catch (Exception e){
            Log.d("LOG-DEBUGGER","MSG : "+e.getMessage());
        }
    }

    private void showImageViaLinkDialog(String URL) {
        ImageView imageView = myDialog.findViewById(R.id.foodImage01);
        LoadImage loadImage = new LoadImage(imageView);
        try {
            loadImage.execute(URL);
            Log.d("LOG-DEBUGGER","MSG : SUCKZZEED");
        }
        catch (Exception e){
            Log.d("LOG-DEBUGGER","MSG : "+e.getMessage());
        }
    }

    private void randomFood(){
        List<Food> foods = MainActivity.getFoods();
        ArrayList<String> ingredients = new ArrayList<String>();

        try {
            ingredients = Main3Activity.getIngredientsList(getApplicationContext());
            Log.d("LOG-DEBUGGER","PASS!!");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        List<Food> selected_foods = Utils.searchFoodByUsingSubset(ingredients, foods);
        try {
            random_food01 = Utils.getFoodRandomly(selected_foods);
        }

        catch (Exception e){
            e.printStackTrace();
            random_food01 = null;
        }
    }

    private void randomAllFood(){
        List<Food> foods = MainActivity.getFoods();
        try {
            random_food = Utils.getFoodRandomly(foods);
            Log.d("LOG-DEBUGGER","PASS");
        }

        catch (Exception e){
            e.printStackTrace();
            random_food = null;
        }
    }
}
