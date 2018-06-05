package com.lzp.reflection;

import android.util.Log;

/**
 * Created by lzp48947 on 2018/6/5.
 */

public class MyConstructor {
    private String str = "aaa";

    public MyConstructor(){}

    public MyConstructor(String str){
        this.str = str;
    }

    public void print(){
        Log.e("reflection", str);
    }
}
