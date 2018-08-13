package com.lzp.dagger2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.lzp.dagger2.dependence.DaggerPhoneComponent;
import com.lzp.dagger2.dependence.Pad;
import com.lzp.dagger2.dependence.Phone;
import com.lzp.dagger2.inject_component.Watch;
import com.lzp.dagger2.module_provides.Person;
import com.lzp.dagger2.named_qualifier.Chai;
import com.lzp.dagger2.named_qualifier.Engine;
import com.lzp.dagger2.named_qualifier.QiEngine;
import com.lzp.dagger2.scop_singleton.SecActivity;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends AppCompatActivity {
    @Inject Watch watch;

    @Inject Watch watch1;

    @Inject Gson gson;

    @Inject Gson gson1;

    @Inject
    @Named(QiEngine.QI_ENGINE)
    Engine qiEngine;

    @Inject
    @Chai
    Engine chaiEngine;

    @Inject
    Phone phone;

    @Inject
    Pad pad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button textView = (Button) findViewById(R.id.tv);
        Button go = (Button) findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecActivity.class));
            }
        });
//        DaggerMainActivityComponent.builder().build().inject(this);
        DaggerMainActivityComponent.builder().phoneComponent(DaggerPhoneComponent.create()).build().inject(this);
        final String jsonData = "{'name':'zhangsan','age':'20'}";
        final Person person = gson.fromJson(jsonData, Person.class);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Dagger2", watch.work());
                Log.i("Dagger2", person.getName());
                Log.i("Dagger2", qiEngine.work());
                Log.i("Dagger2", chaiEngine.work());
                Log.i("Dagger2", watch.hashCode() + "---" + watch1.hashCode());
                Log.i("Dagger2", gson.hashCode() + "---" + gson1.hashCode());
                Log.i("Dagger2", phone.phoneWork());
                Log.i("Dagger2", pad.padWork());
            }
        });
    }
}
