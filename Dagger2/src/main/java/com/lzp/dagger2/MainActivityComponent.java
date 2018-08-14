package com.lzp.dagger2;

import com.lzp.dagger2.dependence_subcomponent.PhoneComponent;
import com.lzp.dagger2.module_provides.GsonModule;
import com.lzp.dagger2.named_qualifier.EngineModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lzp48947 on 2018/8/1.
 */
@Singleton
@Component(modules = {GsonModule.class, EngineModule.class}, dependencies = PhoneComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
