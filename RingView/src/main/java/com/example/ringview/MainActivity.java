package com.example.ringview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RingView mRingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRingView = (RingView) findViewById(R.id.ringView);
//        mRingView.setCircleColor(getResources().getColor(R.color.black));

        mRingView.setAnimator();
    }
}
