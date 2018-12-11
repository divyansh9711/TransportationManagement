package com.example.divyanshsingh.transportationmanagement.fragments;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.divyanshsingh.transportationmanagement.R;
import com.example.divyanshsingh.transportationmanagement.acitivity.AppController;
import com.example.divyanshsingh.transportationmanagement.acitivity.DashboardActivity;
import com.example.divyanshsingh.transportationmanagement.acitivity.SearchVehicleActivity;
import com.example.divyanshsingh.transportationmanagement.utils.CommonProgressDialog;
import com.example.divyanshsingh.transportationmanagement.utils.Constants;
import com.example.divyanshsingh.transportationmanagement.utils.SingleButtonError;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * Created By Divyansh Singh
 */

public class SignupFragment extends Fragment implements View.OnClickListener {

    private View view;

    private EditText email, password,confirmPassword,lastName,firstName,mobile;
    private Button eLogin,eSign;
    private GoogleSignInClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    SharedPreferences prefs;
    private Dialog progressDialog;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        eLogin = view.findViewById(R.id.btnLogin);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        confirmPassword = view.findViewById(R.id.confirm_password);
        firstName = view.findViewById(R.id.name);
        lastName = view.findViewById(R.id.last_name);
        mobile = view.findViewById(R.id.mobile);
        eSign = view.findViewById(R.id.btnSignin);
        eSign.setVisibility(View.GONE);
        progressDialog = CommonProgressDialog.LoadingSpinner(getActivity());
        prefs = AppController.prefs;

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(Objects.requireNonNull(getActivity()));
        mGoogleApiClient = GoogleSignIn.getClient(getActivity(), gso);
        eLogin.setOnClickListener(this);
        eSign.setOnClickListener(this);
        return view;
    }


    private void loginWihtEmailPassword(String email, String password) {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user.isEmailVerified()){
                                getUser(user);
                            }else{
                                verifyAccount();
                            }
                            progressDialog.dismiss();


                        } else {
                            progressDialog.dismiss();
                            SingleButtonError.with(getActivity())
                                    .setMessage(Objects
                                            .requireNonNull(task.getException())
                                            .getMessage())
                                    .hideHeading()
                                    .show();
                        }

                        // ...
                    }
                });


    }
    private void getUser(FirebaseUser user) {
        if(!user.isEmailVerified()){
            verifyAccount();
            return;
        }
        progressDialog.dismiss();
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Constants.isLoggedIn, "true");
        editor.putString(Constants.loggedInUserId, user.getUid());
        editor.putString(Constants.loggedInFirstName, user.getDisplayName());
        editor.putString(Constants.loggedInEmail, user.getEmail());
        //editor.putInt(Constants.loggedInUserType, user.getUserType());
        editor.putString(Constants.loggedInUserMobile, user.getPhoneNumber());
        try{
            editor.putString(Constants.loggedInUserName,user.getDisplayName());
        }catch (Exception ignored){}
        //editor.putInt(Constants.loggedInUserSex, user.getUserSex());


        editor.apply();
        Intent intent = new Intent(getActivity(), SearchVehicleActivity.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }
    private void verifyAccount() {
        mAuth.getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(getActivity(), new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(),"Verification email sent please verify email id before login",Toast.LENGTH_LONG).show();
                            eLogin.setVisibility(View.GONE);
                            eSign.setVisibility(View.VISIBLE);
                        } else {
                        }
                    }
                });

    }
    private void signInWihtEmailPassword(String email,String password){

        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            progressDialog.dismiss();
                            if(user.isEmailVerified()){
                                getUser(user);
                            }else {
                                SingleButtonError.with(getActivity()).setMessage("Please verify email").show();
                                verifyAccount();
                            }


                        } else {
                            progressDialog.dismiss();
                            SingleButtonError.with(getActivity())
                                    .setMessage(Objects
                                            .requireNonNull(task.getException())
                                            .getMessage())
                                    .hideHeading()
                                    .show();
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                if(checkAndCall()){
                    String inputEmail = email.getText().toString();
                    String inputPassword = password.getText().toString();
                    loginWihtEmailPassword(inputEmail,inputPassword);
                }
                break;
            case R.id.btnSignin:
                String inputEmail = email.getText().toString();
                String inputPassword = password.getText().toString();
                signInWihtEmailPassword(inputEmail,inputPassword);
                break;
        }
    }
    private boolean checkAndCall() {
        if(email == null || password == null || firstName == null || lastName == null || mobile == null ){
            SingleButtonError.with(getActivity()).setMessage("Something went wrong please try again").show();
            return false;
        }
        else if(email.getText().toString().equals("") || password.getText().toString().equals("") || firstName.getText().toString().equals("") || lastName.getText().toString().equals("")){
            Toast.makeText(getActivity(),"Please input missing fields !",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(password.getText().toString().length() <= 7){
            Toast.makeText(getActivity(),"Invalid password",Toast.LENGTH_LONG).show();

            return false;
        }
        else if(!password.getText().toString().equals(confirmPassword.getText().toString())){
            Toast.makeText(getActivity(),"Password don't match",Toast.LENGTH_LONG).show();
            confirmPassword.setText("");
            return false;
        }
        else if(!email.getText().toString().contains("@lnmiit.ac.in")){
            Toast.makeText(getActivity(),"Please enter your LNMIIT domain id",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
