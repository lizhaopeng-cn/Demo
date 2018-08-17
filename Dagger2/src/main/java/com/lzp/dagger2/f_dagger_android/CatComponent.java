package com.lzp.dagger2.f_dagger_android;

import dagger.Subcomponent;

/**
 * Created by lzp48947 on 2018/8/15.
 */
//@Subcomponent(modules = CatModule.class)
public interface CatComponent{
//    @Subcomponent.Builder
    interface CatBuilder{
        CatBuilder name(String name);
        CatComponent build();
    }
}
