package com.lzp.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/7/9.
 */

@Module
public class SimpleModule {
    @Provides
    Cooker provideCooker(){
        return new Cooker("李四", "拿铁");
    }
}
