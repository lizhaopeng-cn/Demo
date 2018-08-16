package com.lzp.dagger2;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by lzp48947 on 2018/8/16.
 */

public class MyApp extends Application implements HasActivityInjector{
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerMyAppComponent.create().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
