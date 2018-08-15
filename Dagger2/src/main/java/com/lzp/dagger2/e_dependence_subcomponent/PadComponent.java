package com.lzp.dagger2.e_dependence_subcomponent;

import dagger.Subcomponent;

/**
 * Created by lzp48947 on 2018/8/14.
 */
@Subcomponent(modules = PadModule.class)
public interface PadComponent {
    Pad getPad();
}
