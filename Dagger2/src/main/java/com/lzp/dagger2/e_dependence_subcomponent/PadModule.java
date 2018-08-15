package com.lzp.dagger2.e_dependence_subcomponent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/8/13.
 */
@Module
public class PadModule {
    @Provides
    public Pad providePad(){
        return new Pad();
    }
}
