package com.lzp.dagger2.e_dependence_subcomponent;


import dagger.Module;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/8/14.
 */
@Module
public class MouseModule {
    @Provides
    public Mouse privodeMouse(){
        return new Mouse();
    }
}
