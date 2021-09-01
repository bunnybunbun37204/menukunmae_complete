package com.wfh.menukunmae;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.wfh.menukunmae.tools.Utils;


public class Main5Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private View decorView;
    private Dialog myDialog;
    private String genderString,activityString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        myDialog = new Dialog(this);

        Spinner genderSpinner = findViewById(R.id.gender);
        Spinner activitySpinner = findViewById(R.id.activity);

        genderSpinner.setOnItemSelectedListener(this);
        activitySpinner.setOnItemSelectedListener(this);

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

    public void ShowPopupyx(View view) {
        TextView txtclose03;
        EditText weightText = findViewById(R.id.kg);
        EditText heightText = findViewById(R.id.heightz);
        EditText ageText = findViewById(R.id.years);
        float weight = Float.parseFloat(weightText.getText().toString());
        float height = Float.parseFloat(heightText.getText().toString());
        int age = Integer.parseInt(ageText.getText().toString());
        String gender = convertStringToEng(genderString);
        String activity = convertStringToEng(activityString);

        final float BMR = Utils.calculate_BMR(gender, weight, height, age);
        final float CALS = Utils.calculate_Cals(BMR, activity);

        myDialog.setContentView(R.layout.activity_customdialog_showcal);
        txtclose03 = myDialog.findViewById(R.id.txtclose03);
        TextView txtCals = myDialog.findViewById(R.id.calsText);
        @SuppressLint("DefaultLocale") final String showCalsText = String.format("%.02f", CALS)+" Kcal";
        txtCals.setText(showCalsText);
        txtclose03.setOnClickListener(view1 -> myDialog.dismiss());
        myDialog.show();
    }

    /**
     * <p>Callback method to be invoked when an item in this view has been
     * selected. This callback is invoked only when the newly selected
     * position is different from the previously selected position or if
     * there was no selected item.</p>
     * <p>
     * Implementers can call getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param parent   The AdapterView where the selection happened
     * @param view     The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id       The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getSelectedItem().toString().equals("เพศชาย") || parent.getSelectedItem().toString().equals("เพศหญิง")){
            genderString = parent.getSelectedItem().toString();
            Utils.makeToast("เพศ "+ genderString, getApplicationContext());

        }
        else {
            activityString = parent.getSelectedItem().toString();
            Utils.makeToast("กิจกรรม "+activityString, getApplicationContext());
        }

    }

    /**
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private String convertStringToEng(String str){
        String output = "";
        switch (str) {
            case "เพศชาย":
                output = "male";
                break;
            case "เพศหญิง":
                output = "female";
                break;
            case "นั่งทำงานอยู่กับที่ และไม่ได้ออกกำลังกายเลย":
                output = "nothing";
                break;
            case "ออกกำลังกายหรือเล่นกีฬาเล็กน้อย ประมาณอาทิตย์ละ 1–3 วัน":
                output = "little";
                break;
            case "ออกกำลังกายหรือเล่นกีฬาปานกลาง ประมาณอาทิตย์ละ 3–5 วัน":
                output = "mid";
                break;
            case "ออกกำลังกายหรือเล่นกีฬาอย่างหนัก ประมาณอาทิตย์ละ 6–7 วัน":
                output = "hard";
                break;
            case "ออกกำลังกายหรือเล่นกีฬาอย่างหนักทุกวันเช้าเย็น":
                output = "always";
                break;

        }
        return output;
    }
}