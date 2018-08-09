package com.lzp.dagger2;

import dagger.Component;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/8/3.
 */
@Component
        (modules = ComputerModule.class)
public interface ComputerComponent {
    Computer provideComputer();
}
