package com.shra1.pusherexample;

import android.app.Application;

import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;

public class App extends Application {

    public static PusherOptions options;

    public static Pusher pusher;


    @Override
    public void onCreate() {
        super.onCreate();
        options = new PusherOptions();
        options.setCluster("ap2");
        pusher = new Pusher("85bc23e9583aa31772d7", options);
    }
}
