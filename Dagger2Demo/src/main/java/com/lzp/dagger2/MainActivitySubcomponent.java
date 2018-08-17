package com.lzp.dagger2;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by lzp on 2018/8/17.
 */
@Subcomponent(modules = {AndroidInjectionModule.class, MainActivitySubcomponent.SubModule.class})
public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }

    @Module
    class SubModule {
        @Provides
        Computer providComputer(){
            return new Computer();
        }
    }
}
