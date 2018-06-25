package com.example.lzp.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_main);
//        TextView tv = (TextView) findViewById(R.id.tv);
//        Boolean isDebug = BuildConfig.DEBUG;
//        if(isDebug){
//            tv.setText("Debug:"+isDebug);
//        }else{
//            tv.setText("Release:"+isDebug);
//        }
//        String a = null;
//        try {
//            if(a.equals("aaa")){
//                Log.i("test","123");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        Log.i("test","233");

        RelativeLayout.LayoutParams paramsRL = new RelativeLayout.LayoutParams(100, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsRL.setMargins(50, 50, 50, 50);
        paramsRL.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        TextView textView = new TextView(this);
        textView.setLayoutParams(paramsRL);
        textView.setText("哈哈");

        relativeLayout.addView(textView);
        System.out.print("asdf");

        BindView bindView = new BindView();
        bindView.aTest.i = 5;
        bindView.bTest.i = 10;
        textView.setText("" + bindView.aTest.i + "-" + bindView.bTest.i);
    }
}
