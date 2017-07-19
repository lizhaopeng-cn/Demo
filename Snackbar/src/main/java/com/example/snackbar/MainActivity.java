package com.example.snackbar;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final CoordinatorLayout cl = (CoordinatorLayout) findViewById(R.id.cl);

        List<String> datas = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            datas.add("第"+i+"项");
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,datas));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(fab,"sanckbar",Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Snackbar.make(textView,"你点击了Action",Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

    }

}
