package com.example.padch.hackathonappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ReminderActivity extends AppCompatActivity {

    TextView homeworkText = null;

    Button btnDismiss = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        homeworkText = (TextView)findViewById(R.id.textView5);

        btnDismiss = (Button)findViewById(R.id.buttonDismiss);

        final Intent intent = getIntent();
        String homework = intent.getExtras().get("HOMEWORK").toString();
        String subject = intent.getExtras().get("SUBJECT").toString();

        homeworkText.setText(subject + "-" + homework);

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent backToScreen = new Intent(ReminderActivity.this, MainActivity.class);
                startActivity(backToScreen);
            }
        });

    }
}
