package com.lzp.dagger2;

import javax.inject.Inject;

/**
 * Created by lzp48947 on 2018/7/6.
 */

public class SimpleMaker implements CoffeeMaker {
    Cooker cooker;

    @Inject
    public SimpleMaker(Cooker cooker){
        this.cooker = cooker;
    }

    @Override
    public String makeCoffee() {
        return cooker.make();
    }
}
