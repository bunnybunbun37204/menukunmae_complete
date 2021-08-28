package com.wfh.menukunmae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.wfh.menukunmae.tools.MiscellaneousTools;
import com.wfh.menukunmae.tools.SerializeObject;


public class Main3Activity extends AppCompatActivity {

    private View decorView;
    private AutoCompleteTextView ingredientInput;
    private ListView listIngredientView;
    private String ingredient;
    private static ArrayList<String> ingredientsList;
    private static ArrayAdapter<String> ingredientAdapter;
    private final List<String> ingredients = MainActivity.getIngredientList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initialize();
        inputMethod();
        clickItem();
        removeItem();

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

    public static ArrayList<String> getIngredientsList(Context context) {
        ArrayList<String> output = new ArrayList<String>();
        String ser = SerializeObject.ReadSettings(context, "saveddata.dat");
        if (!ser.equalsIgnoreCase("")) {
            Object obj = SerializeObject.stringToObject(ser);
            // Then cast it to your object and
            if (obj instanceof ArrayList) {
                // Do something
                output = (ArrayList<String>) obj;
            }
        }
        return output;
    }

    private void saveIngredientsList(Context context, ArrayList<String> ingredientsList) {
        String ser = SerializeObject.objectToString(ingredientsList);
        if (ser != null && !ser.equalsIgnoreCase("")) {
            SerializeObject.WriteSettings(context, ser, "saveddata.dat");
        } else {
            SerializeObject.WriteSettings(context, "", "saveddata.dat");
        }
        Log.d("LOG-DEBUGGER", "SAVED");
    }

    private void initialize(){
        listIngredientView = (ListView) findViewById(R.id.list_ingredient_item);

        try {
            ingredientsList = getIngredientsList(getApplicationContext());
        } catch (Exception e) {
            Log.d("LOG-DEBUGGER", "NONE");
            ingredientsList = new ArrayList<String>();
        }
        ingredientAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_list_item_ingredients, R.id.list_ing_component, ingredientsList);

        listIngredientView.setAdapter(ingredientAdapter);
    }

    private void inputMethod(){
        ingredientInput = (AutoCompleteTextView) findViewById(R.id.ingredientInput);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_list_item, R.id.text_view_list_item, ingredients);
        ingredientInput.setAdapter(arrayAdapter);
        ingredientAdapter = new ArrayAdapter<String>(this,
                R.layout.custom_list_item_ingredients, R.id.list_ing_component, ingredientsList);
        ingredientInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                // If the event is a key-down event on the "enter" button
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    ingredient = ingredientInput.getText().toString();

                    if(!ingredientsList.contains(ingredient)){
                        if (ingredients.contains(ingredient)) {
                            ingredientsList.add(ingredient);
                            saveIngredientsList(getApplicationContext(), ingredientsList);
                            listIngredientView.setAdapter(ingredientAdapter);
                        }

                        else {
                            MiscellaneousTools.makeToast("กรุณากรอกวัตถุดิบที่มีอยู่",getApplicationContext());
                        }
                    }

                    else {
                        MiscellaneousTools.makeToast("กรุณาไม่กรอกวัตถุดิบซ้ำ", getApplicationContext());
                    }

                    return true;
                }
                return false;
            }
        });
    }

    private void removeItem() {
        listIngredientView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                MiscellaneousTools.makeToast("Removed: " + ingredientsList.get(pos), getApplicationContext());
                ingredientsList.remove(pos);
                saveIngredientsList(getApplicationContext(), ingredientsList);
                listIngredientView.setAdapter(ingredientAdapter);
                return false;
            }
        });
    }

    private void clickItem() {
       listIngredientView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               MiscellaneousTools.makeToast("กดค้างเพื่อลบเมนู", getApplicationContext());
           }
       });
    }

    public static void setIngredientsList(ArrayList<String> ingredientsList) {
        Main3Activity.ingredientsList = ingredientsList;
    }
}