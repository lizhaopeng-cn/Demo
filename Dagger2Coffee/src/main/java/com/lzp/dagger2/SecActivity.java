package com.lzp.dagger2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by lzp48947 on 2018/7/31.
 */

public class SecActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Computer computer = DaggerComputerComponent.create().provideComputer();
                Toast.makeText(SecActivity.this, computer.work(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
