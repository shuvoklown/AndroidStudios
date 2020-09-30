package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    Button button, textBoxTrigger;
    TextView testButtonCheck, checkboxCheck, switchCheck, testRadio1, testRadio2, testRadio3, testSlider, testTextBox;
    CheckBox checkBox;
    Switch switchTest;
    RadioButton radio1, radio2, radio3;
    SeekBar slider;
    EditText textBox;
    String check;
    Integer value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BasicWidgets");

        button = findViewById(R.id.button);
        button.setText("Button1");
        testButtonCheck = findViewById(R.id.buttonCheck);

        checkBox = findViewById(R.id.checkBox);
        checkboxCheck = findViewById(R.id.checkBoxCheck);

        switchCheck = findViewById(R.id.switchCheck);
        switchTest = findViewById(R.id.switchTest);

        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        testRadio1 = findViewById(R.id.testRadioBtn1);
        testRadio2 = findViewById(R.id.testRadioBtn2);
        testRadio3 = findViewById(R.id.testRadioBtn3);

        testSlider = findViewById(R.id.testSlider);
        slider = findViewById(R.id.slider);

        textBox = findViewById(R.id.textBox);
        textBoxTrigger = findViewById(R.id.textBoxTrigger);
        textBoxTrigger.setText("Update label");
        testTextBox = findViewById(R.id.testTextBox);

        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progressNumber = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressNumber = progress;
                testSlider.setText(String.valueOf(progressNumber));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void setButtonText(View view) {
        testButtonCheck.setText("Button 1 text");

    }

    public void changeCheckbox(View view) {
        if (checkBox.isChecked()) {
            check = "checked";
        } else check = "unchecked";
        checkboxCheck.setText("This checkbox is: "+ check);
    }

    public void changeSwitch(View view) {
        if (switchTest.isChecked()) {
            check = "on";
        } else check = "off";
        switchCheck.setText("This switch is: "+ check);
    }

    public void changeRadioButton1(View view) {
        if (radio1.isChecked()) {
            testRadio1.setText("Radio1 selected");
        } else testRadio1.setText("");
    }

    public void changeRadioButton2(View view) {
        if (radio1.isChecked()) {
            testRadio2.setText("Radio2 selected");
        } else testRadio2.setText("");
    }

    public void changeRadioButton3(View view) {
        if (radio1.isChecked()) {
            testRadio3.setText("Radio3 selected");
        } else testRadio3.setText("");
    }

    public void changeTextBox(View view) {
        testTextBox.setText(textBox.getText());
    }
}
