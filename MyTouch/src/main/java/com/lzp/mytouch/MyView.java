package com.lzp.mytouch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

class MyView extends android.support.v7.widget.AppCompatTextView {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("View","dispatchTouchEvent-down");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("View","dispatchTouchEvent-move");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("View","dispatchTouchEvent-up");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("View","dispatchTouchEvent-cancel");
//                return true;
//                return false;
                break;
        }
//        Log.e("View","dispatchTouchEvent-return:" + super.dispatchTouchEvent(ev));
//        return true;
//        return false;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("View","onTouchEvent-down");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("View","onTouchEvent-move");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("View","onTouchEvent-up");
//                return true;
//                return false;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("View","onTouchEvent-cancel");
//                return true;
//                return false;
                break;
        }
//        Log.e("View","onTouchEvent-return:" + super.onTouchEvent(event));
        return true;
//        return false;
//        return super.onTouchEvent(event);
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
