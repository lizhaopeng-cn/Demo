package com.lzp.glide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        final TextView textView = (TextView) findViewById(R.id.text);
//        String url = "http://file.40017.cn/iflight_search/logo/kscp246200008566562823396216564741117400910.png";
//        Glide.with(this)
//                .load(url)
//                .into(imageView);
//
//        int i = ((ViewGroup.MarginLayoutParams)textView.getLayoutParams()).leftMargin;
//        Toast.makeText(MainActivity.this,"height:" + i, Toast.LENGTH_SHORT).show();
    }
}
