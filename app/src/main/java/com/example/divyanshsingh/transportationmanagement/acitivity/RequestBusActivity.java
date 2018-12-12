package com.example.divyanshsingh.transportationmanagement.acitivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.divyanshsingh.transportationmanagement.R;
import com.example.divyanshsingh.transportationmanagement.utils.DateTimeUtils;

import java.util.Date;

public class RequestBusActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText from,to,reason;
    private TextView timeTv,dateTv;
    private String time,date;
    private int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_bus);

        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        reason = findViewById(R.id.reason_layout);
        timeTv = findViewById(R.id.time);
        timeTv.setText(DateTimeUtils.getCurrentTime());
        dateTv = findViewById(R.id.date);
        dateTv.setText(DateTimeUtils.getCurrentFormattedDate());

        day = DateTimeUtils.getDay(0);

        time = DateTimeUtils.getCurrentTimeId();
        date = DateTimeUtils.getCurrentDateId();

        timeTv.setOnClickListener(this);
        dateTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.time:
                getTime(timeTv);
                break;
            case R.id.date:
                getDate(dateTv);
                break;
        }
    }

    public void getTime(final TextView view) {
        time = "";
        TimePickerDialog timePickerDialog = new TimePickerDialog(RequestBusActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                time = String.valueOf(i) + String.valueOf(i1) + "00";
                if(day == DateTimeUtils.getDay(0) && i < DateTimeUtils.getHour(0)){
                    Toast.makeText(RequestBusActivity.this,getResources().getString(R.string.time_travel),Toast.LENGTH_LONG).show();
                    return;
                }
                view.setText(String.valueOf(i) + ":" + String.valueOf(i1));
            }
        }, 0, 0, true);
        timePickerDialog.setTitle("Time");
        timePickerDialog.show();
    }

    public void getDate(final TextView view) {
        date = "";
        DatePickerDialog datePickerDialog = new DatePickerDialog(RequestBusActivity.this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                String m = "", d = "";
                day = dd;
                m = String.valueOf(mm + 1);
                d = String.valueOf(dd);
                if(dd < DateTimeUtils.getDay(0)){
                    Toast.makeText(RequestBusActivity.this,getResources().getString(R.string.time_travel),Toast.LENGTH_LONG).show();
                    return;
                }
                if (mm < 9) {
                    m = "0" + String.valueOf(mm + 1);
                }
                if (dd < 10) {
                    d = "0" + String.valueOf(dd);
                }

                view.setText(DateTimeUtils.getWeekDay(yy,mm,dd) + ", " +d + "/" + m);
                date = d + m + String.valueOf(yy);
            }
        }, DateTimeUtils.getYear(0), DateTimeUtils.getMonth(0), DateTimeUtils.getDay(0));
        datePickerDialog.setTitle("Day");
        datePickerDialog.show();
    }
}
