package com.example.padch.hackathonappv2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String homework = intent.getExtras().get("HOMEWORK").toString();
        String subject = intent.getExtras().get("SUBJECT").toString();

        Intent remindActivityIntent = new Intent(context.getApplicationContext(),ReminderActivity.class);
        remindActivityIntent.putExtra("HOMEWORK", homework);
        remindActivityIntent.putExtra("SUBJECT",subject);
        remindActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        remindActivityIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

        context.startActivity(remindActivityIntent);
    }
}
