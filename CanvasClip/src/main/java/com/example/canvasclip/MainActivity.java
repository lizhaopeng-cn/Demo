package com.example.canvasclip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyView my_view = findViewById(R.id.my_view);
        my_view.setViewColor(getResources().getColor(R.color.colorAccent),getResources().getColor(R.color.colorPrimary));
    }
}
