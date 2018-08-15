package com.lzp.dagger2.d_scop_singleton;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by lzp48947 on 2018/8/2.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScop {
}
