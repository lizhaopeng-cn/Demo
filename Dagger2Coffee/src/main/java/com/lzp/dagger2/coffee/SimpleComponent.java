package com.lzp.dagger2.coffee;

import dagger.Component;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/7/9.
 */

@Component(modules = SimpleModule.class)
public interface SimpleComponent {
    @Provides
    void inject(MainActivity mainActivity);
}
