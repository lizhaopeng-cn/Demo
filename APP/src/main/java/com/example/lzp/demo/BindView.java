package com.example.lzp.demo;

import java.lang.annotation.Retention;

/**
 * Created by lzp48947 on 2018/5/30.
 */

public class BindView {
    public Test aTest = new Test();
    public Test bTest = new Test();
    public static class Test{
        public int i = 1;
    }
}
