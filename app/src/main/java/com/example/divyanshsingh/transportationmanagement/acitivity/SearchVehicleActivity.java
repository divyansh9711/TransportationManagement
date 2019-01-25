package com.example.divyanshsingh.transportationmanagement.acitivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.divyanshsingh.transportationmanagement.API.APIError;
import com.example.divyanshsingh.transportationmanagement.API.ResponseResolver;
import com.example.divyanshsingh.transportationmanagement.API.RestClient;
import com.example.divyanshsingh.transportationmanagement.R;
import com.example.divyanshsingh.transportationmanagement.models.Location;
import com.example.divyanshsingh.transportationmanagement.models.Timing;
import com.example.divyanshsingh.transportationmanagement.payloads.VehiclePayload;
import com.example.divyanshsingh.transportationmanagement.response.VehicleResponse;
import com.example.divyanshsingh.transportationmanagement.utils.CommonProgressDialog;
import com.example.divyanshsingh.transportationmanagement.utils.DateTimeUtils;

public class SearchVehicleActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView dateTv;
    private TextView timeTv;
    String time, date;
    private Timing timing;
    private EditText origin;
    private Dialog progressDialog;
    private EditText destination;
    private Button searchBuses;
    private int day, x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_vehicle);

        searchBuses = findViewById(R.id.search_bus);
        origin = findViewById(R.id.from);
        destination = findViewById(R.id.to);
        timeTv = findViewById(R.id.time);
        timeTv.setText(DateTimeUtils.getCurrentTime());
        dateTv = findViewById(R.id.date);
        dateTv.setText(DateTimeUtils.getCurrentFormattedDate());
        day = DateTimeUtils.getDay(0);
        progressDialog = CommonProgressDialog.LoadingSpinner(SearchVehicleActivity.this);

        time = DateTimeUtils.getCurrentTimeId();
        date = DateTimeUtils.getCurrentDateId();
        timing = new Timing();
        timing.setStartTime(time);
        timing.setStartDate(date);
        timing.setWeekDay("1");
        x = DateTimeUtils.getDay(0);
        y = DateTimeUtils.getYear(0);
        if ( DateTimeUtils.getWeekDay(y, x, day).equals("Sun") || DateTimeUtils.getWeekDay(y, x, day).equals("Sat")) {
            timing.setWeekDay("2");
        }
        timeTv.setOnClickListener(this);
        dateTv.setOnClickListener(this);
        searchBuses.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_bus:
                progressDialog.show();
                getVehicle();
                break;
            case R.id.time:
                getTime(timeTv);
                break;
            case R.id.date:
                getDate(dateTv);
                break;
        }
    }

    private void getVehicle() {
        final String startLocation = origin.getText().toString();
        final String endLocation = destination.getText().toString();
        VehiclePayload vehiclePayload = new VehiclePayload(startLocation, endLocation, timing,0,10);
        RestClient.getApiInterfaceInt(SearchVehicleActivity.this).getVehicle(vehiclePayload)
                .enqueue(new ResponseResolver<VehicleResponse>(SearchVehicleActivity.this, false, true) {
                    @Override
                    public void success(VehicleResponse vehicleResponse) {
                        Intent intent = new Intent(SearchVehicleActivity.this, DashboardActivity.class);

                        intent.putExtra("VEHICLE_LIST", vehicleResponse.getVehicleList());
                        intent.putExtra("START_LCT",startLocation);
                        intent.putExtra("END_LCT",endLocation);
                        intent.putExtra("TIMING",timing);
                        startActivity(intent);
                        progressDialog.dismiss();
                        finish();
                    }

                    @Override
                    public void failure(APIError error) {
                        progressDialog.dismiss();
                    }
                });
    }

    public void getTime(final TextView view) {
        time = "";
        TimePickerDialog timePickerDialog = new TimePickerDialog(SearchVehicleActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                time = String.valueOf(i) + String.valueOf(i1) + "00";
                if (day == DateTimeUtils.getDay(0) && i < DateTimeUtils.getHour(0)) {
                    Toast.makeText(SearchVehicleActivity.this, getResources().getString(R.string.time_travel), Toast.LENGTH_LONG).show();
                    return;
                }
                timing.setStartTime(time);
                view.setText(String.valueOf(i) + ":" + String.valueOf(i1));
                if(i1 == 0){
                    view.setText(String.valueOf(i)  + ":" + String.valueOf(i1) + "0");
                }
            }
        }, 0, 0, true);
        timePickerDialog.setTitle("Time");
        timePickerDialog.show();
    }

    public void getDate(final TextView view) {
        date = "";
        DatePickerDialog datePickerDialog = new DatePickerDialog(SearchVehicleActivity.this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                String m = "", d = "";
                day = dd;

                m = String.valueOf(mm + 1);
                d = String.valueOf(dd);
                if (dd < DateTimeUtils.getDay(0)) {
                    Toast.makeText(SearchVehicleActivity.this, getResources().getString(R.string.time_travel), Toast.LENGTH_LONG).show();
                    return;
                }
                if (mm < 9) {
                    m = "0" + String.valueOf(mm + 1);
                }
                if (dd < 10) {
                    d = "0" + String.valueOf(dd);
                }
                if (DateTimeUtils.getWeekDay(yy, mm, dd).equals("Sun") || DateTimeUtils.getWeekDay(y, x, day).equals("Sat")) {

                    timing.setWeekDay("2");
                }
                view.setText(DateTimeUtils.getWeekDay(yy, mm, dd) + ", " + d + "/" + m);
                date = d + m + String.valueOf(yy);
            }
        }, DateTimeUtils.getYear(0), DateTimeUtils.getMonth(0), DateTimeUtils.getDay(0));
        datePickerDialog.setTitle("Day");
        datePickerDialog.show();
    }
}

