package com.lzp.bugly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tencent.bugly.crashreport.CrashReport;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        CrashReport.testJavaCrash();
        int b = 0;
        String a = null;
        if(a.equals("a")){
            a = "aa";
        }
    }
}
