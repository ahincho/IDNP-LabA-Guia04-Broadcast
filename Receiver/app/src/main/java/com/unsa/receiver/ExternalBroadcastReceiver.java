package com.unsa.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ExternalBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.unsa.external".equals(intent.getAction())) {
            Log.i("External Receiver", "External Broadcast message received");
            String receivedText = intent.getStringExtra("EXT_MESSAGE");
            Toast.makeText(context, receivedText, Toast.LENGTH_LONG).show();
            MainActivity.tvExternal.setText(receivedText);
        }
    }

}
