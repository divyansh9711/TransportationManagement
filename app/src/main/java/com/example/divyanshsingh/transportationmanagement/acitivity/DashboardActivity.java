package com.example.divyanshsingh.transportationmanagement.acitivity;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divyanshsingh.transportationmanagement.API.APIError;
import com.example.divyanshsingh.transportationmanagement.API.ResponseResolver;
import com.example.divyanshsingh.transportationmanagement.API.RestClient;
import com.example.divyanshsingh.transportationmanagement.R;
import com.example.divyanshsingh.transportationmanagement.adapters.ViewPagerAdapter;
import com.example.divyanshsingh.transportationmanagement.fragments.CurrentFragment;
import com.example.divyanshsingh.transportationmanagement.fragments.SearchResultFragment;
import com.example.divyanshsingh.transportationmanagement.models.Timing;
import com.example.divyanshsingh.transportationmanagement.payloads.VehiclePayload;
import com.example.divyanshsingh.transportationmanagement.response.VehicleResponse;
import com.example.divyanshsingh.transportationmanagement.utils.CommonProgressDialog;
import com.example.divyanshsingh.transportationmanagement.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;

    private ViewPager viewPager;
    private SearchResultFragment resultFragment;
    private DrawerLayout drawer;
    private boolean profile;
    private boolean doubleBackToExitPressedOnce = false;

    private Dialog progressDialog;

    private TextView userName, allBuses, requestExtra, registerComplaints, faq, logOut, addBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        progressDialog = CommonProgressDialog.LoadingSpinner(DashboardActivity.this);

        tabLayout = findViewById(R.id.tab_layout1);
        userName = findViewById(R.id.welcomeName);
        allBuses = findViewById(R.id.all_buses);
        requestExtra = findViewById(R.id.request_extra);
        registerComplaints = findViewById(R.id.register_complaints);
        faq = findViewById(R.id.faq);
        logOut = findViewById(R.id.log_out);
        addBus = findViewById(R.id.add_bus);
        viewPager = findViewById(R.id.container);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this, 1);
        resultFragment = new SearchResultFragment();
        adapter.AddFragment(resultFragment, "Result");
        adapter.AddFragment(new CurrentFragment(), "Current");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setUpNav();
        allBuses.setOnClickListener(this);
        faq.setOnClickListener(this);
        logOut.setOnClickListener(this);
        addBus.setOnClickListener(this);
        registerComplaints.setOnClickListener(this);
        requestExtra.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // .... other stuff in my onResume ....
        this.doubleBackToExitPressedOnce = false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit!", Toast.LENGTH_SHORT).show();
    }
    private void setUpNav(){
        String firstName = "", lastName = "";
        String name = AppController.prefs.getString(Constants.loggedInUserName, "Curious One");
        int index = Objects.requireNonNull(name.indexOf(" "));
        firstName = Objects.requireNonNull(Objects.requireNonNull(name.substring(0, index)));
        lastName = Objects.requireNonNull(name.substring(index + 1, name.length()));
        firstName = firstName.toLowerCase();
        lastName = lastName.toLowerCase();
        String temp = String.valueOf(firstName.charAt(0)).toUpperCase();
        firstName = temp + firstName.substring(1, firstName.length());
        temp = String.valueOf(lastName.charAt(0)).toUpperCase();
        lastName = temp + lastName.substring(1, lastName.length());
        userName.setText(firstName + " " + lastName);
    }


    public void setCurrentItem(int item, boolean smoothScroll) {
        viewPager.setCurrentItem(item, smoothScroll);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setResult(RESULT_OK);
    }

    @Override
    public void onClick(View view) {
        Intent intent ;
        switch (view.getId()) {
            case R.id.all_buses:
                getAllBuses();
                closeDrawer();
                break;
            case R.id.register_complaints:
                intent = new Intent(DashboardActivity.this,ComplaintActivity.class);
                startActivity(intent);
                closeDrawer();
                break;
            case R.id.request_extra:
                closeDrawer();
                intent = new Intent(DashboardActivity.this,RequestBusActivity.class);
                startActivity(intent);
                break;
            case R.id.faq:
                intent = new Intent(DashboardActivity.this,FaqActivity.class);
                startActivity(intent);
                closeDrawer();
                break;
            case R.id.log_out:
                FirebaseAuth.getInstance().signOut();
                closeDrawer();
                intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void closeDrawer(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }
    private void getAllBuses(){
        progressDialog.show();
        VehiclePayload vehiclePayload = new VehiclePayload("","",new Timing(),0,8);
        RestClient.getApiInterfaceInt(DashboardActivity.this).getVehicle(vehiclePayload)
                .enqueue(new ResponseResolver<VehicleResponse>(DashboardActivity.this, false, true) {
                    @Override
                    public void success(VehicleResponse vehicleResponse) {
                        resultFragment.setData(vehicleResponse.getVehicleList());
                        progressDialog.dismiss();
                    }

                    @Override
                    public void failure(APIError error) {
                        progressDialog.dismiss();
                    }
                });
    }
}
