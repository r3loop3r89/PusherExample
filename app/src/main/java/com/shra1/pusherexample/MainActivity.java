package com.shra1.pusherexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import static com.shra1.pusherexample.App.pusher;

public class MainActivity extends AppCompatActivity {
TextView tvMAText;
    private Button bMAStart;
    private Button bMAStop;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMAText = findViewById(R.id.tvMAText);


        bMAStart = (Button) findViewById(R.id.bMAStart);
        bMAStop = (Button) findViewById(R.id.bMAStop);

        intent = new Intent(MainActivity.this, BGService.class);


        bMAStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        bMAStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
    }
}
