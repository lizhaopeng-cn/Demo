package com.lzp.dagger2.d_scop_singleton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lzp.dagger2.R;

import javax.inject.Inject;

/**
 * Created by lzp48947 on 2018/8/2.
 */

public class ThirdActivity extends AppCompatActivity {

    @Inject Computer computer;
    @Inject Computer computer1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        computer = MyApp.get(ThirdActivity.this).getMyAppComponent().getComputer();
        computer1 = MyApp.get(ThirdActivity.this).getMyAppComponent().getComputer();
//        MyApp.get(ThirdActivity.this).getMyAppComponent().inject(ThirdActivity.this);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Dagger2", computer.hashCode() + "---" + computer1.hashCode());
            }
        });
    }
}
