package com.lzp.dagger2.coffee;

/**
 * Created by lzp48947 on 2018/7/9.
 */

public class CoffeeMachine {
    CoffeeMaker coffeeMaker;

    public CoffeeMachine(CoffeeMaker coffeeMaker){
        this.coffeeMaker = coffeeMaker;
    }

    public String makeCoffee(){
        return coffeeMaker.makeCoffee();
    }
}
