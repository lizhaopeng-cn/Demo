package com.lzp.dagger2;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by lzp on 2018/8/17.
 */
@Module
public abstract class MainActivityModule {
    @Provides
    Computer providComputer(){
        return new Computer();
    }
}
