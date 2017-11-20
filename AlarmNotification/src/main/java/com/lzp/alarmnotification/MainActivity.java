package com.lzp.alarmnotification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDefaultNotification(MainActivity.this, 5*1000, "pushContent", "title", "describeContent");
//                NotificationReceiver.setNotification(MainActivity.this, 5*1000, "pushContent", "title", "describeContent");
//                Intent intent = new Intent();
//                intent.setAction("broadcast_notification");
//                intent.putExtra("when", 123321);
//                sendBroadcast(intent);
            }
        });
    }

    public void createDefaultNotification(Context context, long when, String pushContent, String title, String describeContent){
//        BroadcastManager manager = LocalBroadcastManager.getInstance(context);
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("broadcast_notification");
//        NotificationReceiver notificationBroadcastReceiver = new NotificationReceiver();
//        registerReceiver(notificationBroadcastReceiver, filter);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.setAction("broadcast_notification");
        intent.putExtra("when", when);
        intent.putExtra("pushContent", pushContent);
        intent.putExtra("title", title);
        intent.putExtra("describeContent", describeContent);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long triggerAtTime = (long) (SystemClock.elapsedRealtime() + when);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
//        long triggerAtTime1 = (long) (System.currentTimeMillis() + when);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtTime1, pendingIntent);
    }
}
