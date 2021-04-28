package com.lzp.mytouch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

// https://www.jianshu.com/p/38015afcdb58
// https://upload-images.jianshu.io/upload_images/944365-aea821bbb613c195.png
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FrameLayout frameLayout = findViewById(R.id.my_view_group);
//        frameLayout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.e("ViewGroup","setOnTouchListener");
//                return true;
//            }
//        });
//        frameLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("ViewGroup","setOnClickListener");
//            }
//        });
//
//        TextView textView = findViewById(R.id.my_view);
//        textView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.e("View","setOnTouchListener");
//                return false;
//            }
//        });
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("View","setOnClickListener");
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("Activity","dispatchTouchEvent-down");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("Activity","dispatchTouchEvent-move");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("Activity","dispatchTouchEvent-up");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("Activity","dispatchTouchEvent-cancel");
//                return true;
//                return false;
                break;
        }
//        Log.e("Activity","dispatchTouchEvent-return:" + super.dispatchTouchEvent(ev));
//        return true;
//        return false;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onUserInteraction() {
//        Log.e("Activity","dispatchTouchEvent-onUserInteraction");
        super.onUserInteraction();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("Activity","onTouchEvent-down");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("Activity","onTouchEvent-move");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("Activity","onTouchEvent-up");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("Activity","onTouchEvent-cancel");
//                return true;
//                return false;
                break;
        }
//        Log.e("Activity","onTouchEvent-return:" + super.onTouchEvent(event));
//        return true;
//        return false;
        return super.onTouchEvent(event);
    }

}
