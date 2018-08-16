package com.lzp.dagger2;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by lzp48947 on 2018/8/16.
 */
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        MainActivityModule.class,
        SecondActivityModule.class
})
public interface MyAppComponent {
    void inject(MyApp myApp);
}
