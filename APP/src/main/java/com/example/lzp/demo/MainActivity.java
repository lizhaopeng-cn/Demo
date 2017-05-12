package com.example.lzp.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        Boolean isDebug = BuildConfig.DEBUG;
        if(isDebug){
            tv.setText("Debug:"+isDebug);
        }else{
            tv.setText("Release:"+isDebug);
        }
    }
}
