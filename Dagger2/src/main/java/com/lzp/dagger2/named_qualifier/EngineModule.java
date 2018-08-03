package com.lzp.dagger2.named_qualifier;

import com.lzp.dagger2.scop_singleton.AppScop;

import javax.inject.Named;
import javax.inject.Singleton;

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
