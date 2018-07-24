package com.lzp.dagger2;

import dagger.Component;

/**
 * Created by lzp48947 on 2018/7/9.
 */

@Component(modules = SimpleModule.class)
public interface SimpleComponent {
    CoffeeMachine inject(MainActivity mainActivity);
}
