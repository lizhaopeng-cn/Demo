package com.lzp.dagger2;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/8/17.
 */
@Module(subcomponents = {
        MainActivitySubcomponent.class,
        SecondActivitySubcomponent.class
})
public class MyAppModule {
    @Singleton
    @Provides
    Context provideContext(Application application){
        return application;
    }
}
