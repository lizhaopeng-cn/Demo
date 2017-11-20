package com.lzp.alarmnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;

/**
 * Created by lzp on 2017/11/20.
 */

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("broadcast_notification")){
            long when = intent.getLongExtra("when", 0);
            String pushContent = intent.getStringExtra("pushContent");
            String title = intent.getStringExtra("title");
            String describeContent = intent.getStringExtra("describeContent");
            setNotification(context, when, pushContent, title, describeContent);
        }
    }

    public void setNotification(Context context, long when, String pushContent, String title, String describeContent) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("when", when);

        PendingIntent contentIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(TextUtils.isEmpty(title) ? context.getString(R.string.app_name) : title)
                .setContentText(describeContent)
                .setWhen(when)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(contentIntent)
                .build();
        nm.notify((int) System.currentTimeMillis(), notification);
    }
}
