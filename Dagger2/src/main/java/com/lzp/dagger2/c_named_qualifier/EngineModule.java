package com.lzp.dagger2.c_named_qualifier;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/8/1.
 */
@Module
public class EngineModule {
    @Provides
    @Named(QiEngine.QI_ENGINE)
    public Engine provideQiYouEngin(){
        return new QiEngine();
    }

    @Provides
    @Chai
    public Engine provideChaiYouEngin(){
        return new ChaiEngine();
    }
}
