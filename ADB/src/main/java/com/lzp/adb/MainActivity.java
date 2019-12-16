package com.lzp.adb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        查看所有进程
        adb shell ps

        查看指定包名的进程
        adb shell ps | grep "com.tencent.qq"

        强杀正在运行的进程：
        指定pid强杀：
        adb shell kill pid

        指定包名强杀：
        adb shell am force-stop com.taobao.taobao

        启动acitvity:
        adb shell am start -n com.tencent.qq/.HomeActivity

*/

    }
}
