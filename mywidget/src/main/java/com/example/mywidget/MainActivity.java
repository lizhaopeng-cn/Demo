package com.example.mywidget;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.tv);
        ListView color = (ListView) findViewById(R.id.color);
        Drawable background = textView.getBackground();
        ColorDrawable colorDrawable = (ColorDrawable) background;
        int c = colorDrawable.getColor();
        Log.i("test",c+"-");
    }
}
