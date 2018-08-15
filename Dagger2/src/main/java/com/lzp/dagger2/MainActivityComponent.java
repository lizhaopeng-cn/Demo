package com.lzp.dagger2;

import com.lzp.dagger2.e_dependence_subcomponent.PhoneComponent;
import com.lzp.dagger2.b_module_provides.GsonModule;
import com.lzp.dagger2.c_named_qualifier.EngineModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lzp48947 on 2018/8/1.
 * dependencies（无@Scope的@Component可以依赖有@Scope的@Component，有@Scope的@Component只能依赖有@Scope的@Component，并且两者的@Scope不能相同）
 */
@Singleton
@Component(modules = {GsonModule.class, EngineModule.class}, dependencies = PhoneComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
