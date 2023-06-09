package com.example.alertdialogcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Telephony;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public Button button;

    private Handler mHandler;

     MediaPlayer mp;

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";

    int permissionsCode = 42;

    private boolean color;
    public Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        String[] permissions = {android.Manifest.permission.READ_SMS, android.Manifest.permission.SYSTEM_ALERT_WINDOW,
                Manifest.permission.POST_NOTIFICATIONS, Manifest.permission.RECEIVE_SMS, Manifest.permission.ACCESS_FINE_LOCATION};
        ActivityCompat.requestPermissions(this, permissions, permissionsCode);
         mp = MediaPlayer.create(this, R.raw.sneeze);
         color = false;
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

         registerReceiver(broadcastReceiver, new IntentFilter("SMS_RECEIVED"));


      //   WindowManager manager = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE));
     //    WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
      //   localLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
       //  localLayoutParams.gravity = Gravity.TOP;
       //  localLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
       //          WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
       //  localLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
       //  localLayoutParams.height = (int) (50 * getResources().getDisplayMetrics().scaledDensity);
      //   localLayoutParams.format = PixelFormat.TRANSPARENT;
     //    customViewGroup view = new customViewGroup(this);
     //    manager.addView(view, localLayoutParams);

    }



    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

                Log.d("INNER BROADCAST", "INNER");
            showDialog();

                    mp.start();
            }

    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    public void showDialog(){

        dialog.setCancelable(true);
        dialog.setContentView(color? R.layout.dialog_0 : R.layout.dialog_3);
        //dialog.setContentView(R.layout.dialog_3);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        TextView tw = (TextView) dialog.findViewById(color ? R.id.textView2 : R.id.msg3);
        tw.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ");
        dialog.show();
        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock screenLock = ((PowerManager)getSystemService(POWER_SERVICE)).newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        screenLock.acquire();
        screenLock.release();
        color = !color;

      //  AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.myDialogThemePrior_2);
     //   builder.setMessage("Lorem ipsum dolor sit amet, consectetur adipisci elit, sed eiusmod tempor incidunt ut labore et dolore magna aliqua.")
     //           .setPositiveButton("Whatever", (DialogInterface.OnClickListener)(dialog, which)->{
     //               finish();
     //           });
     //   AlertDialog alertDialog = builder.create();
    //    alertDialog.show();
     //   alertDialog.getWindow().setGravity(Gravity.BOTTOM);
    //    alertDialog.show();
     //   mp.start();
    }



}