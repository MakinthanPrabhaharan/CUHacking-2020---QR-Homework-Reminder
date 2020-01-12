package com.example.padch.hackathonappv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import org.w3c.dom.Text;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView = null;

    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);

        setContentView(scannerView);

        textView = (TextView)findViewById(R.id.textView);

        if(scannerView == null){
            scannerView = new ZXingScannerView(this);
            setContentView(scannerView);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(scannerView == null){
            scannerView = new ZXingScannerView(this);
            setContentView(scannerView);
        }

        scannerView.setResultHandler(this);
        scannerView.startCamera();

    }

    @Override
    protected void onDestroy() {
        scannerView.stopCamera();
        super.onDestroy();
    }

    @Override
    public void handleResult(Result result) {

        String resultString = result.getText();
        String splitString[] = resultString.split("-");

        String Subject = splitString[0];
        String Homework = splitString[1];

        Intent intent = new Intent(MainActivity.this, Activity2.class);
        intent.putExtra("SUBJECT",Subject);
        intent.putExtra("HOMEWORK", Homework);

        startActivity(intent);



    }
}
