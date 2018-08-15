package com.lzp.dagger2.a_inject_component;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by lzp48947 on 2018/8/1.
 */
@Singleton
public class Watch {
    @Inject
    public Watch(){

    }

    public String work(){
        return "手表";
    }
}
