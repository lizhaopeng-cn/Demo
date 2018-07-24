package com.lzp.dagger2;

/**
 * Created by lzp48947 on 2018/7/6.
 */

public class Cooker {
    String name; //咖啡师名字
    String coffeeKind; //制作咖啡的类型
    public Cooker(String name,String coffeeKind){
        this.name = name;
        this.coffeeKind = coffeeKind;
    }
    public String make(){
        return name +" make " + coffeeKind; //咖啡师制作Coffee的过程
    }
}
