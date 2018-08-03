package com.lzp.dagger2;

import dagger.Component;
import dagger.Provides;

/**
 * Created by lzp48947 on 2018/8/3.
 */
@Component
public interface ComputerComponent {
    @Provides
    Computer provideComputer();
}
