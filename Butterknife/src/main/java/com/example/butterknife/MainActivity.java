package com.example.butterknife;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.text)
    TextView text;

    private boolean isTrue = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        submit();
        text.setText("qwer");

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                isTrue = true;
//            }
//        });
    }

    @OnClick(R.id.button) void submit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(false);
        builder.setTitle("提示");
        builder.setMessage("只有登录才能提交答案，确定继续吗？");
        builder.setPositiveButton("继续", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(isTrue){
                    isTrue = false;
                    submit();
                }
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                finish();
            }
        });
        builder.show();
    }
}
