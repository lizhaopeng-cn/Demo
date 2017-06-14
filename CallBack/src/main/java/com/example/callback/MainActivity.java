package com.example.callback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvText;
    private ListView lvList;
    private MyAdapter myAdapter;
    private List<String> datas;
    private UpdataCallBack updataCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvText = (TextView) findViewById(R.id.tv_item);
        lvList = (ListView) findViewById(R.id.lv_list);

        datas = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            datas.add(String.valueOf(i));
        }

        updataCallBack = new UpdataCallBack() {
            @Override
            public void getText(String s) {
//                tvText.setText("I am back!"+s);
                Log.e("text","I am back!"+s);
            }
        };
        myAdapter = new MyAdapter(this,datas,updataCallBack);
        lvList.setAdapter(myAdapter);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                tvText.setText("onItemClick:"+i);
                Log.e("test","onItemClick"+i);
            }
        });
    }
}
