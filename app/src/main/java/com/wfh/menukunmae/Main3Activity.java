package com.wfh.menukunmae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import com.wfh.menukunmae.tools.SerializeObject;


public class Main3Activity extends AppCompatActivity {

    private View decorView;
    private EditText ingredientInput;
    private String ingredient;
    private ArrayList<String> ingredientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        try {
            ingredientsList = getIngredientsList(getApplicationContext());
        }
        catch (Exception e){
            Log.d("LOG-DEBUGGER","NONE");
            ingredientsList = new ArrayList<String>();
        }
        Log.i("LOG-INFO","TEST : "+ingredientsList);
        ingredientInput = (EditText) findViewById(R.id.ingredientInput);
        ingredientInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                // If the event is a key-down event on the "enter" button
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Log.d("LOG-DEBUGGER","KEY PRESS");

                    ingredient = ingredientInput.getText().toString();

                    if(ingredient != "" || ingredient == null){
                        ingredientsList.add(ingredient);
                        saveIngredientsList(getApplicationContext(), ingredientsList);
                    }

                    Log.d("LOG-DEBUGGER","TEXT : "+ingredientsList);
                    return true;
                }
                return false;
            }
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

    private ArrayList<String> getIngredientsList(Context context) {
        ArrayList<String> output = new ArrayList<String>();
        String ser = SerializeObject.ReadSettings(context, "saveddata.dat");
        if (ser != null && !ser.equalsIgnoreCase("")) {
            Object obj = SerializeObject.stringToObject(ser);
            // Then cast it to your object and
            if (obj instanceof ArrayList) {
                // Do something
                output = (ArrayList<String>) obj;
            }
        }
        return output;
    }

    private void saveIngredientsList(Context context, ArrayList<String> ingredientsList){
        String ser = SerializeObject.objectToString(ingredientsList);
        if (ser != null && !ser.equalsIgnoreCase("")) {
            SerializeObject.WriteSettings(context, ser, "saveddata.dat");
        } else {
            SerializeObject.WriteSettings(context, "", "saveddata.dat");
        }
        Log.d("LOG-DEBUGGER","SAVED");
    }

}