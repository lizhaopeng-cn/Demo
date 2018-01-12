package com.example.suspensionball;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton fab;
    private FloatingActionButton fabNote;
    private FloatingActionButton fabHotel;
    private FloatingActionButton fabTrain;
    private FloatingActionButton fabFlight;
    private ObjectAnimator clAnimator;
    private ObjectAnimator fabAnimator;
    private ObjectAnimator fabNoteAnimator;
    private ObjectAnimator fabHotelAnimator;
    private ObjectAnimator fabTrainAnimator;
    private ObjectAnimator fabFlightAnimator;
    private boolean isFabOpen = false;
    private float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();//屏幕分辨率容器
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        density = mDisplayMetrics.density;

        tv = (TextView) findViewById(R.id.tv);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabNote = (FloatingActionButton) findViewById(R.id.fab_note);
        fabHotel = (FloatingActionButton) findViewById(R.id.fab_hotel);
        fabTrain = (FloatingActionButton) findViewById(R.id.fab_train);
        fabFlight = (FloatingActionButton) findViewById(R.id.fab_flight);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);

        List<String> datas = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            datas.add("第"+i+"项");
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,datas));

        coordinatorLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFabOpen){
                    setFabClose();
                }
            }
        });

        coordinatorLayout.setClickable(false);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFabOpen){ //关闭
                    setFabClose();
                }else{ //打开
                    setFabOpen();
                }
            }
        });
        fabNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("速记");
            }
        });
        fabHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("酒店");
            }
        });
        fabTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("火车");
            }
        });
        fabFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("机票");
            }
        });

    }

    public void setFabClose(){
        isFabOpen = false;
        coordinatorLayout.setClickable(false);
        clAnimator = ObjectAnimator.ofArgb(coordinatorLayout, "backgroundColor", ContextCompat.getColor(this, R.color.gray_1), 0).setDuration(500);
        fabAnimator = ObjectAnimator.ofFloat(fab, "rotation", 135, -20, 0).setDuration(500);
        fabNoteAnimator = ObjectAnimator.ofFloat(fabNote, "translationY", density * -100, 0).setDuration(500);
        fabHotelAnimator = ObjectAnimator.ofFloat(fabHotel, "translationY", density * -150, 0).setDuration(500);
        fabTrainAnimator = ObjectAnimator.ofFloat(fabTrain, "translationY", density * -200, 0).setDuration(500);
        fabFlightAnimator = ObjectAnimator.ofFloat(fabFlight, "translationY", density * -250, 0).setDuration(500);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(clAnimator, fabAnimator, fabNoteAnimator, fabHotelAnimator, fabTrainAnimator, fabFlightAnimator);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                fabNote.setVisibility(View.GONE);
                fabHotel.setVisibility(View.GONE);
                fabTrain.setVisibility(View.GONE);
                fabFlight.setVisibility(View.GONE);
            }
        });

    }

    public void setFabOpen(){
        isFabOpen = true;
        coordinatorLayout.setClickable(true);
        clAnimator = ObjectAnimator.ofArgb(coordinatorLayout, "backgroundColor", 0, ContextCompat.getColor(this, R.color.gray_1)).setDuration(500);
        fabAnimator = ObjectAnimator.ofFloat(fab, "rotation", 0, 155, 135).setDuration(500);
        fabNoteAnimator = ObjectAnimator.ofFloat(fabNote, "translationY", 0, density * -100).setDuration(500);
        fabHotelAnimator = ObjectAnimator.ofFloat(fabHotel, "translationY", 0, density * -150).setDuration(500);
        fabTrainAnimator = ObjectAnimator.ofFloat(fabTrain, "translationY", 0, density * -200).setDuration(500);
        fabFlightAnimator = ObjectAnimator.ofFloat(fabFlight, "translationY", 0, density * -250).setDuration(500);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(clAnimator, fabAnimator, fabNoteAnimator, fabHotelAnimator, fabTrainAnimator, fabFlightAnimator);

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationEnd(animation);
                fabNote.setVisibility(View.VISIBLE);
                fabHotel.setVisibility(View.VISIBLE);
                fabTrain.setVisibility(View.VISIBLE);
                fabFlight.setVisibility(View.VISIBLE);
            }
        });
        set.start();
    }
}
