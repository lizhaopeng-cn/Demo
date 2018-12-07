package com.lzp.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.*

class MainActivity : AppCompatActivity() {
    val c: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        TextView() tv = clearFindViewByIdCache R.id.textView
        Log.i("kotlin", "1 + 2 = ${sum(1,2)}")
        Log.i("kotlin", "1 + 2 = ${sum(1,2)}")
        val a: Int = 4
//        a = 5
        var b = 6
        var f = a + b
        b = 7
        var boo = false
        boo = a.equals(b)
        boo = a == (b)
        boo = a === (b)
        var str: String = "123"
        var str1: String = "123"
        boo = str == (str1)

        print(a)
        getStringLength(8)
//        getStringLength1(9)
//        openList(a["b", "c"] Int)
        val list = listOf<Int>(2,4,6)
        for (num in list) {
            var sum = 0
            sum =+ num
        }
    }

    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    fun sum1(a: Int, b: Int) = a + b

    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            // `obj` 在该条件分支内自动转换成 `String`
            return obj.length
        }
        // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
        return null
    }

//    fun getStringLength1(obj: Int): Int? {
//        if (obj is String) {
//            // `obj` 在该条件分支内自动转换成 `String`
//            return obj.length
//        }
//        // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
//        return null
//    }

    fun openList(args: Array<String>) {
        val list = listOf("aaa", *args)
    }
}
