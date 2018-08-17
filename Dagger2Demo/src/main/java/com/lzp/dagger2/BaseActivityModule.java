package com.lzp.dagger2;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by lzp48947 on 2018/8/17.
 */
@Module(subcomponents = BaseActivityComponent.class)
public abstract class BaseActivityModule {
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeMainActivitytInjector();

    @ContributesAndroidInjector(modules = SecondActivityModule.class)
    abstract SecondActivity contributeSecondActivityInjector();
}
