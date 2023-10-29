package com.unsa.sender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage = findViewById(R.id.txtMessage);
    }

    public void sendBroadcastOnClick(View v) {
        String message = txtMessage.getText().toString();
        if (!message.equals("")) {
            Intent intent = new Intent("com.unsa.external");
            intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            Log.i("Sender App", "External Broadcast Message Sent");
            intent.putExtra("EXT_MESSAGE", "External: " + message);
            sendBroadcast(intent);
            Toast.makeText(this, "External Broadcast Message Sent", Toast.LENGTH_SHORT).show();
            txtMessage.setText("");
        }
    }

}