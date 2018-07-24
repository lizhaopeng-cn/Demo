package com.lzp.spannablestring;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.tv);

        String str = "0123456789";
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        Drawable drawable = getResources().getDrawable(R.drawable.group);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        CenteredImageSpan imgSpan = new CenteredImageSpan(drawable);
        builder.setSpan(imgSpan, 3, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE );//不管是哪一种都不会影响1跟3的颜色
        tv.setText(builder);

        if(true){
            str = "1";
        }else{

        }
    }
}
