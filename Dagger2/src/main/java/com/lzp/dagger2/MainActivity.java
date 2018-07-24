package com.lzp.dagger2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CoffeeMachine coffeeMachine = new CoffeeMachine(new Cooker("张三", "摩卡"));
                CoffeeMachine coffeeMachine = DaggerSimpleComponent.builder().build().inject(MainActivity.this);
                Toast.makeText(MainActivity.this, coffeeMachine.makeCoffee(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
