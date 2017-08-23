package com.lzp.timer;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;
    private TextView tvHandler;
//    private long allTime = (24*60*60*3+10)*1000;
    private long timerTime = 10*1000;
    private long handlerTimer = 10*1000;

    private int oneDay = 24*60*60*1000;
    private int oneHour = 60*60*1000;
    private int oneMinute = 60*1000;
    private int oneSecond = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimer = (TextView) findViewById(R.id.tv_timer);
        tvHandler = (TextView) findViewById(R.id.tv_handler);

        setTimer();
        setHandler();

    }

    private void setTimer() {
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(timerTime >= 0){
                            tvTimer.setText(calculatorTimer(timerTime));
                            timerTime-=1000;
                        }else{
                            timer.cancel();
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask,0,1000);
    }

    private void setHandler() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                if(handlerTimer >= 0){
                    tvHandler.setText(calculatorTimer(handlerTimer));
                    handlerTimer-=1000;
                    handler.postDelayed(this,1000);
                }
            }
        };
        tvHandler.postDelayed(runnable, 1000);
    }

    private String calculatorTimer(long allTime) {
        int days = (int) allTime / oneDay;
        int hours = (int) (allTime - (days * oneDay)) / oneHour;
        int minutes = (int) (allTime - (days * oneDay) - (hours * oneHour)) / oneMinute;
        int seconds = (int) (allTime - (days * oneDay) - (hours * oneHour) - (minutes * oneMinute)) / oneSecond;

        return days + "天" + hours + "时" + minutes + "分" + seconds + "秒";
    }
}
