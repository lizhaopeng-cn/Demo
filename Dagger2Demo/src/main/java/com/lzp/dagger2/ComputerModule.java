package com.lzp.dagger2;

/**
 * Created by lzp48947 on 2018/8/3.
 */
//@Module
public class ComputerModule {
//    @Provides
    Computer provideComputer(){
        return new Computer();
    }
}
