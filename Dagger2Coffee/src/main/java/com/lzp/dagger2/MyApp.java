package com.lzp.dagger2;

import android.app.Application;
import android.util.Log;

/**
 * Created by lzp48947 on 2018/8/16.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Dagger2", "aaa");
    }
}
