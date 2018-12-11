package com.example.divyanshsingh.transportationmanagement.acitivity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.divyanshsingh.transportationmanagement.R;
import com.example.divyanshsingh.transportationmanagement.adapters.ViewPagerAdapter;
import com.example.divyanshsingh.transportationmanagement.fragments.LoginFragment;
import com.example.divyanshsingh.transportationmanagement.fragments.SignupFragment;


/**
 * Created By Divyansh Singh
 */

public class LoginActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private AppBarLayout appbBarLayout;
    private ViewPager viewPager;
    private LoginFragment loginFragment;
    private boolean profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tabLayout = findViewById(R.id.tab_layout1);
        appbBarLayout = findViewById(R.id.appbar);
        viewPager = findViewById(R.id.container);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this, 1);
        loginFragment = new LoginFragment();
        adapter.AddFragment(loginFragment, "Login");
        adapter.AddFragment(new SignupFragment(), "Sign-Up");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginFragment.onActivityResult(requestCode, resultCode, data);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        viewPager.setCurrentItem(item, smoothScroll);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setResult(RESULT_OK);
    }
}