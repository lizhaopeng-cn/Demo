package com.lzp.dagger2.scop_singleton;

import android.app.Application;
import android.content.Context;

/**
 * Created by lzp48947 on 2018/8/2.
 */

public class MyApp extends Application {

    private MyAppComponent myAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        myAppComponent = DaggerMyAppComponent.builder().build();
    }

    public static MyApp get(Context context){
        return (MyApp) context.getApplicationContext();
    }

    public MyAppComponent getMyAppComponent(){
        return myAppComponent;
    }
}
