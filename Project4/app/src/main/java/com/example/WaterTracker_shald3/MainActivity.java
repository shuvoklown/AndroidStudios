package com.example.WaterTracker_shald3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TabHost waterTabs;
    TextView gender, age, weight, date, goalText, progressText;
    RadioButton male, female;
    Spinner ageValues;
    EditText weightValue;
    Button add, substract;
    Button done;
    SeekBar tracker;
    CalendarView calendar;
    float goalGender, weightEntered, ageSelected;
    int goal, count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = 0;
        setTitle("Water Tracking App");
        waterTabs = findViewById(R.id.appTabs);
        waterTabs.setup();
        setupTabs();
        setupTab1UI();
        setupTab2UI();
        setupTab3UI();
    }

    public void setupTabs() {
        TabHost.TabSpec profileTab = waterTabs.newTabSpec("Profile");
        profileTab.setContent(R.id.tab1);
        profileTab.setIndicator("Profile");
        waterTabs.addTab(profileTab);

        TabHost.TabSpec trackerTab = waterTabs.newTabSpec("Tracker");
        trackerTab.setContent(R.id.tab2);
        trackerTab.setIndicator("Tracker");
        waterTabs.addTab(trackerTab);

        TabHost.TabSpec calendarTab = waterTabs.newTabSpec("Calendar");
        calendarTab.setContent(R.id.tab3);
        calendarTab.setIndicator("Calendar");
        waterTabs.addTab(calendarTab);
    }

    public void setupTab1UI(){

        gender = findViewById(R.id.gender);
        gender.setText("I am:");
        male = findViewById(R.id.male);
        male.setText("Male");
        female = findViewById(R.id.female);
        female.setText("Female");

        age = findViewById(R.id.age);
        age.setText("My age (yrs):");
        ageValues = findViewById(R.id.ageSpinner);

        ArrayList<Integer> ageList = new ArrayList<>();
        for (int i=0; i<=100; i++) {
            ageList.add(i);
        }

        ArrayAdapter <Integer> ageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ageList);
        ageValues.setAdapter(ageAdapter);

        weight = findViewById(R.id.weight);
        weight.setText("My weight (lbs):");
        weightValue = findViewById(R.id.weightValue);

        done = findViewById(R.id.done);
        done.setText("Done");
    }

    public void setupTab2UI() {
        date = findViewById(R.id.date);
        date.setText(Calendar.getInstance().getTime().toString());
        goalText = findViewById(R.id.goalText);
        goalText.setText("Fill in details in profile tab and press 'Done' to find out your goal");
        tracker = findViewById(R.id.seekBar);
        tracker.setMax(0);
        tracker.setMax(goal);
        tracker.setProgress(goal);
        tracker.animate();
        add = findViewById(R.id.plus);
        add.setText("+1 cup");
        substract = findViewById(R.id.minus);
        substract.setText("-1 cup");
        progressText = findViewById(R.id.progress);
        tracker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBar.setEnabled(false);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBar.setEnabled(true);
            }
        });
    }

    public void setupTab3UI() {
        calendar = findViewById(R.id.calendarView);

    }

    public void maleSelected(View view) {
        if (male.isChecked()) {
            goalGender = (float) 15.5;
        }
    }

    public void femaleSelected(View view) {
        if (female.isChecked()) {
            goalGender = (float) 11.5;
        }
    }

    public void submitted(View view) {
        weightEntered = Integer.parseInt(weightValue.getText().toString());
        ageSelected =Integer.parseInt(ageValues.getSelectedItem().toString());
        weight.setText("Weight: "+(weightEntered));
        age.setText("Age: "+(ageSelected));
        gender.setText("I am: "+(goalGender));
        goalText.setText("Daily goal: "+(int) goal+" cups of water");

        goal = (int) (((0.0833 * weightEntered) + goalGender)/2);
    }

    public void addACup(View view) {

        if (count+1 < goal) {
            count++;
            progressText.setText("You are still "+(goal-count)+" cups short of achieving your goal");
        } else {
            progressText.setText("Congratulation!! You have achieved your goal!");
            count = goal;
            progressText.setTextColor(Color.WHITE);
            waterTabs.setBackgroundColor(Color.parseColor("#add8e6"));
        }
        tracker.setEnabled(true);
        tracker.setProgress(0);
        tracker.setMax(goal);
        tracker.setProgress(count);
    }

    public void substractACup(View view) {
        if (count > 0) {
            count--;
        }
        int remainder = goal - count;
        progressText.setText("You are still "+remainder+" cups short of achieving your goal");
        tracker.setEnabled(true);
        tracker.setProgress(0);
        tracker.setMax(goal);
        tracker.setProgress(count);
        progressText.setTextColor(Color.parseColor("#add8e6"));
        waterTabs.setBackgroundColor(Color.WHITE);
    }

}
