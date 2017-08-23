package com.lzp.countdowntimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvSecond;
    private TextView tvMinute;
    private TextView tvHour;
    private TextView tvDay;

    private long allTime = 259210000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSecond = (TextView) findViewById(R.id.tv_second);
        tvMinute = (TextView) findViewById(R.id.tv_minute);
        tvHour = (TextView) findViewById(R.id.tv_hour);
        tvDay = (TextView) findViewById(R.id.tv_day);

        formatTime(allTime);
    }

    /*
     * 毫秒转化时分秒毫秒
     */
    public void formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        tvSecond.setText(String.valueOf(second));
        tvMinute.setText(String.valueOf(minute));
        tvHour.setText(String.valueOf(hour));
        tvDay.setText(String.valueOf(day));
    }
    /*
     * 毫秒转化时分秒毫秒
     */
//    public static String formatTime(Long ms) {
//        Integer ss = 1000;
//        Integer mi = ss * 60;
//        Integer hh = mi * 60;
//        Integer dd = hh * 24;
//
//        Long day = ms / dd;
//        Long hour = (ms - day * dd) / hh;
//        Long minute = (ms - day * dd - hour * hh) / mi;
//        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
//        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;
//
//        StringBuffer sb = new StringBuffer();
//        if(day > 0) {
//        }
//        sb.append(day+"天");
//        if(hour > 0) {
//        }
//        sb.append(hour+"小时");
//        if(minute > 0) {
//        }
//        sb.append(minute+"分");
//        if(second > 0) {
//        }
//        sb.append(second+"秒");
//        if(milliSecond > 0) {
//        }
//        sb.append(milliSecond+"毫秒");
//        return sb.toString();
//    }

    /**
     * 取消倒计时
     * @param v
     */
    public void oncancel(View v) {
        secondTimer.cancel();
    }

    /**
     * 开始倒计时
     * @param v
     */
    public void restart(View v) {
        secondTimer.start();
    }

    private CountDownTimer secondTimer = new CountDownTimer(allTime, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            tvSecond.setText(String.valueOf(millisUntilFinished / 1000));
            allTime = allTime - millisUntilFinished / 1000;
        }

        @Override
        public void onFinish() {
            if(allTime != 0){
                minuteTimer.start();
            }
        }
    };

    private CountDownTimer minuteTimer = new CountDownTimer(60000 * 60, 1000 * 60) {

        @Override
        public void onTick(long millisUntilFinished) {
            tvMinute.setText(String.valueOf(millisUntilFinished / 60 / 1000));
        }

        @Override
        public void onFinish() {
            if(allTime != 0){

            }

        }
    };

    private CountDownTimer hourTimer = new CountDownTimer(60000 * 60 * 24, 1000 * 60 * 24) {

        @Override
        public void onTick(long millisUntilFinished) {
            tvHour.setText(String.valueOf(millisUntilFinished / 24 / 60 / 1000));
        }

        @Override
        public void onFinish() {
            if(allTime == 0){

            }else{
//                allTime = allTime -
            }
        }
    };
}
