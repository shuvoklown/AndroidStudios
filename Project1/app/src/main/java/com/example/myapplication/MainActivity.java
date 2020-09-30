package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button hello;
    TextView world;
    View parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My First App");
        hello = (Button) findViewById(R.id.button);
        hello.setText("Hello");
        world = (TextView) findViewById(R.id.textView);
    }

    public void sayHello(View view) {
        world.setText("Hello World!");
    }
}
