package com.lzp.dagger2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.lzp.dagger2.e_dependence_subcomponent.DaggerMouseComponent;
import com.lzp.dagger2.e_dependence_subcomponent.DaggerPhoneComponent;
import com.lzp.dagger2.a_inject_component.Watch;
import com.lzp.dagger2.b_module_provides.Person;
import com.lzp.dagger2.c_named_qualifier.Chai;
import com.lzp.dagger2.c_named_qualifier.Engine;
import com.lzp.dagger2.c_named_qualifier.QiEngine;
import com.lzp.dagger2.d_scop_singleton.SecActivity;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import dagger.Lazy;

public class MainActivity extends AppCompatActivity {
    @Inject
    Watch watch;

    @Inject
    Lazy<Watch> watch1;

    @Inject
    Provider<Watch> watch2;

    @Inject Gson gson;

    @Inject Gson gson1;

    @Inject
    @Named(QiEngine.QI_ENGINE)
    Engine qiEngine;

    @Inject
    @Chai
    Engine chaiEngine;

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
//        DaggerMouseComponent.builder().build().getMainActivityComponent().inject(this);
        final String jsonData = "{'name':'zhangsan','age':'20'}";
        final Person person = gson.fromJson(jsonData, Person.class);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Dagger2", "" + watch.hashCode());
                Log.i("Dagger2", "" + watch1.get().hashCode());
                Log.i("Dagger2", "" + watch2.get().hashCode());
                Log.i("Dagger2", person.getName());
                Log.i("Dagger2", qiEngine.work());
                Log.i("Dagger2", chaiEngine.work());
                Log.i("Dagger2", watch.hashCode() + "---" + watch1.hashCode());
                Log.i("Dagger2", gson.hashCode() + "---" + gson1.hashCode());
                Log.i("Dagger2", DaggerPhoneComponent.create().getPhone().phoneWork());
                Log.i("Dagger2", DaggerMouseComponent.create().getPadComponent().getPad().padWork());
                Log.i("Dagger2", DaggerMouseComponent.create().getMose().mouseWork());
                Log.i("Dagger2", "aaa");
            }
        });
    }
}
