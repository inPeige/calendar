package com.mkit.calendar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText startTime;
    private EditText endTime;
    private TextView add;
    String title = "title=乌克兰 vs 英国&";
    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        add = findViewById(R.id.add);
        add.setOnClickListener(this);
        int write_calendar = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR);
        int read_calendar = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALENDAR);
        if (write_calendar != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_CALENDAR},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        if (read_calendar != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_CALENDAR},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }

    @Override
    public void onClick(View v) {
        String sTime = startTime.getText().toString();
        String eTime = endTime.getText().toString();
        if (TextUtils.isEmpty(sTime)) {
            Toast.makeText(v.getContext(), "请输入开始时间", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(eTime)) {
            Toast.makeText(v.getContext(), "请输入结束时间", Toast.LENGTH_LONG).show();
            return;
        }
        CalendarReminderUtils.addCalendarEvent(this, title, sTime, "www.baidu.com", new CalendarReminderUtils.CalendarCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFail() {

            }
        });
    }


}
