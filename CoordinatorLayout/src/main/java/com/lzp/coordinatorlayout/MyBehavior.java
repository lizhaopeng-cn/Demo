package com.lzp.coordinatorlayout;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by lzp on 2017/7/12.
 */

public class MyBehavior extends CoordinatorLayout.Behavior<Button>{
    private int width;
    public MyBehavior(Context context, AttributeSet attrs){
        super(context, attrs);
        width = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        return dependency instanceof TempView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {
        int top = dependency.getTop();
        int left = dependency.getLeft();
        int x = width - left - child.getWidth();
        int y = top;
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) child.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        child.setLayoutParams(layoutParams);
        return true;
    }


    //每次dependency位置发生变化，都会执行onDependentViewChanged方法
//    @Override
//    public boolean onDependentViewChanged(CoordinatorLayout parent, Button btn, View dependency) {
//
//        //根据dependency的位置，设置Button的位置
//
//        int top = dependency.getTop();
//        int left = dependency.getLeft();
//
//        int x = width - left - btn.getWidth();
//        int y = top;
//
//        setPosition(btn, x, y);
//        return true;
//    }
//
//    private void setPosition(View v, int x, int y) {
//        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) v.getLayoutParams();
//        layoutParams.leftMargin = x;
//        layoutParams.topMargin = y;
//        v.setLayoutParams(layoutParams);
//    }
}
