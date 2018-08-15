package com.lzp.dagger2.c_named_qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;


/**
 * Created by lzp48947 on 2018/8/1.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Chai {
}
