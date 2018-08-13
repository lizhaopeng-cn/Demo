package com.lzp.dagger2.dependence;

import javax.inject.Inject;

/**
 * Created by lzp48947 on 2018/8/13.
 */

public class Phone {
    @Inject
    public Phone(){

    }
    public String phoneWork(){
        return "手机工作了";
    }
}
