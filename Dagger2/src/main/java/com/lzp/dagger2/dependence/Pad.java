package com.lzp.dagger2.dependence;

import javax.inject.Inject;

/**
 * Created by lzp48947 on 2018/8/13.
 */

public class Pad {
    @Inject
    public Pad(){

    }
    public String padWork(){
        return "平板工作了";
    }
}
