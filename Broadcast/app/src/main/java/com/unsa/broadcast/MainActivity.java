package com.unsa.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textBroadcast;
    private IntentFilter chargingIntentFilter;
    private ChargingBroadcastReceiver chargingBroadcastReceiver;

    public void init() {
        textBroadcast = findViewById(R.id.textBroadcast);
        chargingIntentFilter = new IntentFilter();
        chargingIntentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        chargingIntentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        chargingBroadcastReceiver = new ChargingBroadcastReceiver();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private class ChargingBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean isCharging = (action.equals(Intent.ACTION_POWER_CONNECTED));
            showCharging(isCharging);
        }
    }

    private void showCharging(boolean isCharging) {
        if (isCharging) {
            System.out.println("Device's charging");
            textBroadcast.setText(R.string.device_charging);
            textBroadcast.setTextColor(Color.parseColor("#0000FF"));
        } else {
            System.out.println("Device isn't charging");
            textBroadcast.setText(R.string.device_isnt_charging);
            textBroadcast.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(chargingBroadcastReceiver, chargingIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(chargingBroadcastReceiver);
    }

}