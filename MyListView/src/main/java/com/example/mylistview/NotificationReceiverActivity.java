package com.example.mylistview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class NotificationReceiverActivity extends BroadcastReceiver {
    private BroadcastReceiver reciver;
    private Bundle bundle;
    private static final String YES_ACTION = "android.intent.action.YES_ACTION";
    private static final String NO_ACTION = "android.intent.action.NO_ACTION";
    private static final String MAYBE_ACTION = "android.intent.action.MAYBE_ACTION";



    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        ClipData clip = null;
        android.content.ClipboardManager clipboard;
        clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        Bundle b = intent.getExtras();




        if(YES_ACTION.equals(action)) {
            clip = ClipData.newPlainText("lable", b.getString("1"));
            Toast.makeText(context, "Doing yes action "+b.getString("1"), 0).show();
            clipboard.setPrimaryClip(clip);

        } else if(MAYBE_ACTION.equals(action)) {
            clip = ClipData.newPlainText("lable", b.getString("2"));
            Toast.makeText(context, "Doing maybe action "+b.getString("2"), 0).show();

            clipboard.setPrimaryClip(clip);


        } else if(NO_ACTION.equals(action)) {
            clip = ClipData.newPlainText("lable", b.getString("3"));
            Toast.makeText(context, "Doing no action "+b.getString("3"), 0).show();
            clipboard.setPrimaryClip(clip);


        }
//        clipboard.setPrimaryClip(clip);
    }
}