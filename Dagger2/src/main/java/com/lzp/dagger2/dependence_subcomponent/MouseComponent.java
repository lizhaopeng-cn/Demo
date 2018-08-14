package com.lzp.dagger2.dependence_subcomponent;

import dagger.Component;

/**
 * Created by lzp48947 on 2018/8/14.
 */
@Component(modules = MouseModule.class)
public interface MouseComponent {
    Mouse getMose();
    PadComponent getPadComponent();
}
