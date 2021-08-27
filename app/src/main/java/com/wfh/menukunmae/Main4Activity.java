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
import com.wfh.menukunmae.tools.MiscellaneousTools;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    private View decorView;
    private Food random_food;
    private  List<Food> foods;
    private ArrayList<String> ingredients;
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
        randomFood();
        final String URL = random_food.getFood_img();
        TextView txtclose, textInfo;
        Button btnFollow;
        myDialog.setContentView(R.layout.custom_dialog);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        textInfo = (TextView) myDialog.findViewById(R.id.textViewx);
        btnFollow = (Button) myDialog.findViewById(R.id.btnFollow);
        btnFollow.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        showImageViaLink(URL);
        textInfo.setText(random_food.getFood_name());
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

    private void randomFood(){
        foods = MainActivity.getFoods();
        ingredients = new ArrayList<String>();

        try {
            ingredients = Main3Activity.getIngredientsList(getApplicationContext());
            Log.d("LOG-DEBUGGER","PASS!!");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        List<Food> selected_foods = MiscellaneousTools.searchFoodByUsingSubset(ingredients, foods);
        random_food = MiscellaneousTools.getFoodRandomly(selected_foods);

        Log.i("LOG-INFO", "SELECTED_FOOD : " + selected_foods);
        Log.i("LOG-INFO", "RANDOM_FOOD : " + random_food);
    }
}
