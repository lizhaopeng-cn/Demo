package com.lzp.dagger2.e_dependence_subcomponent;


import dagger.Module;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/8/13.
 */
@Module(includes = PadModule.class)
public class PhoneModule {
    @Provides
    public Phone providePhone(){
        return new Phone();
    }
}
