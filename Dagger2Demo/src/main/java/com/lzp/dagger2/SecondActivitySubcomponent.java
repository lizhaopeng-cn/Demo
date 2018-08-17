package com.lzp.dagger2;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by lzp on 2018/8/17.
 */
@Subcomponent(modules = {SecondActivityModule.class})
public interface SecondActivitySubcomponent extends AndroidInjector<SecondActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SecondActivity>{}
//
//    @Module
//    class SubModule{
//        @Provides
//        String provoideName(){
//            return SecondActivity.class.getName();
//        }
//    }
}
