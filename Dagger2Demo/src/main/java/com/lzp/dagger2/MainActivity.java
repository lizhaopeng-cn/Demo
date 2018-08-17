package com.lzp.dagger2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
    @Inject
    Computer computer;

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
//                Toast.makeText(MainActivity.this, , Toast.LENGTH_SHORT).show();
                Log.i("Dagger2",computer.work());
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

    }
}
