package com.example.padch.hackathonappv2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class Activity2 extends AppCompatActivity {

    static int alarmsset = 0;

    TextView ipAndSubjectTextView = null;

    TextView textView = null;

    ArrayList<String> arrayList = new ArrayList<>();

    String ipAddress = "";
    String socketData = "";

    Socket socket = null;


    EditText hourField =null;
    EditText minuteField = null;

    Button btnOk = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        btnOk = (Button)findViewById(R.id.btnOK);

        hourField = (EditText)findViewById(R.id.hourTextField);
        minuteField = (EditText)findViewById(R.id.minuteTextField);


        ipAndSubjectTextView = (TextView)findViewById(R.id.textView2);

        Intent intent = getIntent();

        final String subject = intent.getExtras().get("SUBJECT").toString();
        final String homework = intent.getExtras().get("HOMEWORK").toString();

        ipAndSubjectTextView.setText(subject + "-" + homework);



        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm(subject,homework);

                finish();
                startActivity(new Intent(Activity2.this, MainActivity.class));
            }
        });



    }

    public void setAlarm(String subject, String homework){
        int hour = Integer.parseInt(hourField.getText().toString());
        int minute = Integer.parseInt(minuteField.getText().toString());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);

        if(calendar.before(Calendar.getInstance())){
            calendar.add(Calendar.DATE,1);
        }

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(Activity2.this, AlarmReceiver.class);
        intent.putExtra("SUBJECT",subject);
        intent.putExtra("HOMEWORK", homework);
        final int intid = (int)System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),intid,intent,PendingIntent.FLAG_UPDATE_CURRENT);


        if(Build.VERSION.SDK_INT >= 19){
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }




    }




}
