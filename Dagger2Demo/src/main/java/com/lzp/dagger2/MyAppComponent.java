package com.lzp.dagger2;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by lzp48947 on 2018/8/16.
 */
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        BaseActivityModule.class
})
public interface MyAppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MyApp myApp);
        MyAppComponent build();
    }


    void inject(MyApp myApp);
}
