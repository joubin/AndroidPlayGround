package com.example.mylistview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

public class NotificationReceiverActivity extends BroadcastReceiver {
    private BroadcastReceiver reciver;
    private Bundle bundle;
    private static final String YES_ACTION = "YES";
    private static final String NO_ACTION = "NO";
    private static final String MAYBE_ACTION = "MAYBE";
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        ClipData clip = null;
        android.content.ClipboardManager clipboard;
        clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);


        if(YES_ACTION.equals(action)) {
            clip = ClipData.newPlainText("lable", "yes");
        } else if(MAYBE_ACTION.equals(action)) {
            clip = ClipData.newPlainText("lable", "maybe");
        } else if(NO_ACTION.equals(action)) {
            clip = ClipData.newPlainText("lable", "no");
        }
        clipboard.setPrimaryClip(clip);
    }
}