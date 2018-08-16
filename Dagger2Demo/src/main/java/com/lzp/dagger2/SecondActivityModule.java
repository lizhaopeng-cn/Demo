package com.lzp.dagger2;

import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by lzp on 2018/8/17.
 */
@Module(subcomponents = SecondActivitySubcomponent.class)
public abstract class SecondActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(SecondActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindSecondActivityInjectorFactory(SecondActivitySubcomponent.Builder builder);
}
