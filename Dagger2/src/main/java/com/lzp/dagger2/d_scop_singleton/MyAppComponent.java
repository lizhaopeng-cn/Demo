package com.lzp.dagger2.d_scop_singleton;

import dagger.Component;

/**
 * Created by lzp48947 on 2018/8/2.
 */
@AppScop//或者 @Singleton
@Component(modules = {MyAppModule.class})
public interface MyAppComponent {
//    void inject(SecActivity secActivity);
//    void inject(ThirdActivity thirdActivity);
    Computer getComputer();
}
