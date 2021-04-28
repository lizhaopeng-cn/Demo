package com.lzp.mytouch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

class MyViewGroup extends FrameLayout {

    public MyViewGroup(@NonNull Context context) {
        super(context);
    }

    public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("ViewGroup","dispatchTouchEvent-down");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("ViewGroup","dispatchTouchEvent-move");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("ViewGroup","dispatchTouchEvent-up");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("ViewGroup","dispatchTouchEvent-cancel");
//                return true;
//                return false;
                break;
        }
//        Log.e("ViewGroup","dispatchTouchEvent-return:" + super.dispatchTouchEvent(ev));
//        return true;
//        return false;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("ViewGroup","onInterceptTouchEvent-down");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("ViewGroup","onInterceptTouchEvent-move");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("ViewGroup","onInterceptTouchEvent-up");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("ViewGroup","onInterceptTouchEvent-cancel");
//                return true;
//                return false;
                break;
        }
//        Log.e("ViewGroup","onInterceptTouchEvent-return:" + super.onInterceptTouchEvent(ev));
//        return true;
//        return false;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("ViewGroup","onTouchEvent-down");
//                return true;
                return false;
//                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("ViewGroup","onTouchEvent-move");
                return true;
//                return false;
//                break;
            case MotionEvent.ACTION_UP:
                Log.e("ViewGroup","onTouchEvent-up");
//                return true;
                return false;
//                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("ViewGroup","onTouchEvent-cancel");
//                return true;
//                return false;
                break;
        }
//        Log.e("ViewGroup","onTouchEvent-return:" + super.onTouchEvent(event));
//        return true;
//        return false;
        return super.onTouchEvent(event);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
    }
}
