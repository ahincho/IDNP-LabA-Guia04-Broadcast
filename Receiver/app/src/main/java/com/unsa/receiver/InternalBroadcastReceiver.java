package com.unsa.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class InternalBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.unsa.internal".equals(intent.getAction())) {
            Log.i("Internal Receiver", "Internal Broadcast message received");
            String receivedText = intent.getStringExtra("INT_MESSAGE");
            Toast.makeText(context, receivedText, Toast.LENGTH_LONG).show();
            MainActivity.tvInternal.setText(receivedText);
        }
    }

}
