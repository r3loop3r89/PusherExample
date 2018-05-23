package com.shra1.pusherexample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import static com.shra1.pusherexample.App.pusher;

public class BGService extends Service {
    public BGService() {
    }

    private NotificationManager notificationManager;
    private String CHANNEL_ID = "myChannelId";
    private CharSequence CHANNEL_NAME = "shrawansChannel";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Channel channel = pusher.subscribe("my-channel");
        channel.bind("my-event", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                if (notificationManager == null) {
                    notificationManager =
                            (NotificationManager)
                                    getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(
                            CHANNEL_ID,
                            CHANNEL_NAME,
                            NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(notificationChannel);
                }

                Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);

                PendingIntent pendingIntent = PendingIntent.getActivity(
                        getApplicationContext(),
                        101,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

                NotificationCompat.Builder builder
                        = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Pusher Message")
                        .setAutoCancel(true)
                        //.setContentIntent(pendingIntent)
                        .setContentText(data)
                        .setPriority(NotificationManager.IMPORTANCE_DEFAULT);

                notificationManager.notify(45, builder.build());

            }
        });
        pusher.connect();
        return START_STICKY;
    }
}
