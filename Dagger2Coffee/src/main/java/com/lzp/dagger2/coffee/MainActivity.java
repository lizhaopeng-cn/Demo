package com.lzp.dagger2.coffee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lzp.dagger2.R;
import com.lzp.dagger2.SecActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
//    @Inject
//    CoffeeMachine coffeeMachine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CoffeeMachine coffeeMachine = new CoffeeMachine(new Cooker("张三", "摩卡"));
//                DaggerSimpleComponent.builder().build().inject(MainActivity.this);
//                Toast.makeText(MainActivity.this, coffeeMachine.makeCoffee(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SecActivity.class));
            }
        });

    }
}
