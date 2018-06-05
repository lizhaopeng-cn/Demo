package com.lzp.reflection;

import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int i = 1;
    public int a = 2;
    protected int[] array;
    public List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Class c = MainActivity.class;
        Class c1 = new MainActivity().getClass();
        Class c2 = null;
        try {
            c2 = Class.forName("com.lzp.reflection.MainActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Log.e("reflection", "Class----------------");
        Log.e("reflection", c.toString());
        Log.e("reflection", c1.toString());
        Log.e("reflection", c2.toString());
        Log.e("reflection", c.getName());
        Log.e("reflection", c.getSimpleName());
        Log.e("reflection", c.getCanonicalName());

        try {
            //属性
//            Log.e("reflection", "Field----------------");
//            //只能获取public
            Log.e("reflection", String.valueOf(c.getField("list")));
            Log.e("reflection", String.valueOf(c.getField("list").getName()));
//            //public和private
            Log.e("reflection", String.valueOf(c.getDeclaredField("i")));
            Log.e("reflection", String.valueOf(c.getDeclaredField("i").getName()));
//            //public包括父
//            for(Field field : c.getFields()){
//                Log.e("reflection", String.valueOf(field));
//            }
//            //private & protected 不包括父
//            for(Field field : c.getDeclaredFields()){
//                Log.e("reflection", String.valueOf(field));
//            }
            //赋值
//            // public
//            Log.e("reflection", "a赋值前："+c.getField("a").getInt(this));
//            c.getDeclaredField("a").setInt(this, 4);
//            Log.e("reflection", "a赋值前："+c.getField("a").getInt(this));
//            //private setAccessible(true)
//            Log.e("reflection", "i赋值前："+c.getDeclaredField("i").getInt(this));
//            c.getDeclaredField("i").setAccessible(true);
//            c.getDeclaredField("i").setInt(this, 3);
//            Log.e("reflection", "i赋值后："+c.getDeclaredField("i").getInt(this));

            //方法 同上
//            Log.e("reflection", "Method----------------");
//            Log.e("reflection", String.valueOf(c.getMethod("onAttachFragment", Fragment.class)));
//            Log.e("reflection", String.valueOf(c.getMethod("onAttachFragment", Fragment.class).getName()));
//            Log.e("reflection", String.valueOf(c.getDeclaredMethod("onCreate", Bundle.class)));
//            Log.e("reflection", String.valueOf(c.getDeclaredMethod("onCreate", Bundle.class).getName()));
//            public包括父
//            for(Method method : c.getMethods()){
//                Log.e("reflection", String.valueOf(method));
//            }
////            //private & protected 不包括父
//            for(Method method : c.getDeclaredMethods()){
//                Log.e("reflection", String.valueOf(method));
//            }
            Method m = c.getDeclaredMethod("onCreate", Bundle.class);
            //获取方法参数信息bug
//            Parameter[] parameters = m.getParameters();
//            for(Parameter parameter : parameters){
//                Log.e("reflection", "" + parameter.getModifiers() + parameter.getParameterizedType() + parameter.getName());
//            }
            //获取所有参数类型
            Class<?>[] classes = m.getParameterTypes();
            for(Class type : classes){
                Log.e("reflection", type.getName());
            }
            //获取方法返回值
            Log.e("reflection", m.getReturnType().getName());
            //获取方法修饰符
            Log.e("reflection", Modifier.toString(m.getModifiers()));
            //执行方法
            Method method = c.getMethod("add", int.class, int.class);
            Log.e("reflection", "" + method.invoke(this, 3,4));

            //构造函数 （没有父类）
            //在 Java 反射机制中有两种方法可以用来创建类的对象实例：Class.newInstance() 和 Constructor.newInstance()。官方文档建议开发者使用后面这种方法，下面是原因。
            //Class.newInstance() 只能调用无参的构造方法，而 Constructor.newInstance() 则可以调用任意的构造方法。
            //Class.newInstance() 通过构造方法直接抛出异常，而 Constructor.newInstance() 会把抛出来的异常包装到 InvocationTargetException 里面去，这个和 Method 行为一致。
            //Class.newInstance() 要求构造方法能够被访问，而 Constructor.newInstance() 却能够访问 private 修饰的构造器。
//            Log.e("reflection", "Constructor----------------");

            Class clz = MyConstructor.class;
            MyConstructor myConstructor = (MyConstructor) clz.newInstance();
            myConstructor.print();
            Constructor constructor = clz.getConstructor(String.class);
            MyConstructor myConstructor1 = (MyConstructor) constructor.newInstance("bbb");
            myConstructor1.print();

            //属性类型
//            Log.e("reflection", "FieldType----------------");
//            Log.e("reflection", String.valueOf(c.getDeclaredField("list").getType()));
//            Log.e("reflection", String.valueOf(c.getDeclaredField("list").getGenericType()));
//            //修饰符
//            Log.e("reflection", "Modifiers----------------");
//            Log.e("reflection", String.valueOf(Modifier.toString(c.getDeclaredField("array").getModifiers())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int add(int a, int b){
        int c = a + b;
        return c;
    }

//    public class MyConstructor{
//        private String str = "aaa";
//
//        public MyConstructor(){}
//
//        public MyConstructor(String str){
//            this.str = str;
//        }
//
//        public void print(){
//            Log.e("reflection", str);
//        }
//    }
}
