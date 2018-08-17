package com.lzp.dagger2.f_dagger_android;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/8/15.
 */
//@Module
class CatModule {
//    @Provides
    public Cat provideCat(){
        return new Cat();
    }
}
