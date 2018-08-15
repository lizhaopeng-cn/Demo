package com.lzp.dagger2.b_module_provides;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/8/1.
 */
@Module
public class GsonModule {
    @Provides
    @Singleton
    public Gson provideGson(){
        return new Gson();
    }
}
