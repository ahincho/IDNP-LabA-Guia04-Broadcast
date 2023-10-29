package com.unsa.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtMessage;
    public static TextView tvInternal;
    public static TextView tvExternal;
    private InternalBroadcastReceiver internal = new InternalBroadcastReceiver();
    private ExternalBroadcastReceiver external = new ExternalBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage = findViewById(R.id.txtMessage);
        tvInternal = findViewById(R.id.txtInternal);
        tvExternal = findViewById(R.id.txtExternal);
        IntentFilter internalFilter = new IntentFilter("com.unsa.internal");
        IntentFilter externalFilter = new IntentFilter("com.unsa.external");
        registerReceiver(internal, internalFilter);
        registerReceiver(external, externalFilter);
    }

    public void sendBroadcastOnClick(View v) {
        String message = txtMessage.getText().toString();
        if (!message.equals("")) {
            Intent intent = new Intent("com.unsa.internal");
            intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            Log.i("Receiver App", "Internal Broadcast Message Sent");
            intent.putExtra("INT_MESSAGE", "Internal: " + message);
            sendBroadcast(intent);
            Toast.makeText(this, "Internal Broadcast Message Sent", Toast.LENGTH_SHORT).show();
            txtMessage.setText("");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(internal);
        unregisterReceiver(external);
    }

}