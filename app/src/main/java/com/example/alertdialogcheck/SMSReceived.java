package com.example.alertdialogcheck;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SMSReceived extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("OUTER BROADCAST", "OUTER");

        context.sendBroadcast(new Intent(("SMS_RECEIVED")));
    }
}
