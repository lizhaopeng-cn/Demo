package com.lzp.dagger2;

import javax.inject.Inject;

/**
 * Created by lzp48947 on 2018/8/3.
 */

public class Computer {
    @Inject
    public Computer(){

    }

    public String work(){
        return "电脑工作";
    }
}
