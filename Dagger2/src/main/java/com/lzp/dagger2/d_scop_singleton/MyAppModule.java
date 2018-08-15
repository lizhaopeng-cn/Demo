package com.lzp.dagger2.d_scop_singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/8/13.
 */
@Module
public class MyAppModule {
    @Provides
    @AppScop
    public Computer provideGson(){
        return new Computer();
    }
}
