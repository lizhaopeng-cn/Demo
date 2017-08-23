package com.lzp.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private long allTime = (24*60*60*3+10)*1000;

    private int oneDay = 24*60*60*1000;
    private int oneHour = 60*60*1000;
    private int oneMinute = 60*1000;
    private int oneSecond = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(countDownTimer(allTime));
                        allTime-=1000;
                    }
                });
            }
        };
        timer.schedule(timerTask,0,1000);
    }

    private String countDownTimer(long allTime) {
        int days = (int) allTime / oneDay;
        int hours = (int) (allTime - (days * oneDay)) / oneHour;
        int minutes = (int) (allTime - (days * oneDay) - (hours * oneHour)) / oneMinute;
        int seconds = (int) (allTime - (days * oneDay) - (hours * oneHour) - (minutes * oneMinute)) / oneSecond;

        return days + "天" + hours + "时" + minutes + "分" + seconds + "秒";
    }
}
