package com.lzp.dagger2.f_dagger_android;

import dagger.Component;

/**
 * Created by lzp48947 on 2018/8/16.
 */
@Component
public interface AnimalComponent {
    Animal getAnimal();
    CatComponent.CatBuilder getCatBuilder();
}
