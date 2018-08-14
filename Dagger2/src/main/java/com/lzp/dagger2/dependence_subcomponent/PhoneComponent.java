package com.lzp.dagger2.dependence_subcomponent;

import dagger.Component;

/**
 * Created by lzp48947 on 2018/8/13.
 */
@Component(modules = PhoneModule.class)
public interface PhoneComponent {
    Phone getPhone();
}
