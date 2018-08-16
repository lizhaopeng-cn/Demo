package com.lzp.dagger2.f_dagger_android;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/8/16.
 */
@Module(subcomponents = CatComponent.class)
public class AnimalModule {
    @Provides
    public Animal provideAnimal(){
        return new Animal();
    }

    @Provides
    public CatComponent provideCatComponent(CatComponent.CatBuilder catBuilder){
        return catBuilder.name("喵喵").build();
    }
}
