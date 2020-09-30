package com.example.project3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView redLbl, blueLbl, greenLbl, alphaLbl, redVal, blueVal, greenVal, alphaVal;
    SeekBar redBar, blueBar, greenBar, alphaBar;
    View colorView;
    int red, green, blue;
    float alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Color Fun Project");

        setRGBLabels();
        setColorView();
        setRGBBars();
    }

    public void setRGBLabels() {
        redLbl = findViewById(R.id.redLabel);
        redLbl.setText("Red");
        redVal = findViewById(R.id.redValue);
        redVal.setText("0");

        blueLbl = findViewById(R.id.blueLabel);
        blueLbl.setText("Blue");
        blueVal = findViewById(R.id.blueValue);
        blueVal.setText("0");

        greenLbl = findViewById(R.id.greenLabel);
        greenLbl.setText("Green");
        greenVal = findViewById(R.id.greenValue);
        greenVal.setText("0");

        alphaLbl = findViewById(R.id.alphaLabel);
        alphaLbl.setText("Alpha");
        alphaVal = findViewById(R.id.alphaValue);
        alphaVal.setText("0");
    }

    public void setColorView() {
        colorView = findViewById(R.id.colorView);
        colorView.setBackgroundColor(Color.rgb(0, 0, 0));
        colorView.setAlpha(0);
    }

    public void setBars(SeekBar bar, final TextView progressLbl) {
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressLbl.setText(String.valueOf(progress));
                setViewColor(seekBar, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setRGBBars() {
        redBar = findViewById(R.id.redBar);
        setBars(redBar, redVal);
        blueBar = findViewById(R.id.blueBar);
        setBars(blueBar, blueVal);
        greenBar = findViewById(R.id.greenBar);
        setBars(greenBar, greenVal);
        alphaBar = findViewById(R.id.alphaBar);
        setBars(alphaBar, alphaVal);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setViewColor(SeekBar bar, int progress) {
        if (bar.getId() == redBar.getId()) {
            red = (progress*255/100);
        } else if (bar.getId() == greenBar.getId()) {
            green = (progress*255/100);
        } else if (bar.getId() == blueBar.getId()) {
            blue = (progress*255/100);
        } else {
            alpha = (float)(progress/100.00);
        }
        colorView.setAlpha(alpha);
        colorView.setBackgroundColor(Color.rgb(red, green, blue));

    }
}
