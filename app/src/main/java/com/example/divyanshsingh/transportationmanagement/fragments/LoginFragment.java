package com.example.divyanshsingh.transportationmanagement.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * Created By Divyansh Singh
 */

public class LoginFragment extends Fragment implements View.OnClickListener {

    private View view;
    private EditText email, password;
    private ImageView gLogin;
    private Button eLogin;
    private GoogleSignInClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    SharedPreferences prefs;
    private TextView forgotPassword, verify;
    private Dialog progressDialog;
    private GoogleSignInOptions gso;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        gLogin = view.findViewById(R.id.google_login);
        eLogin = view.findViewById(R.id.btnLogin);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        verify = view.findViewById(R.id.verify);

        progressDialog = CommonProgressDialog.LoadingSpinner(getActivity());
        prefs = AppController.prefs;

        mAuth = FirebaseAuth.getInstance();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(Objects.requireNonNull(getActivity()));
        mGoogleApiClient = GoogleSignIn.getClient(getActivity(), gso);
        gLogin.setOnClickListener(this);
        eLogin.setOnClickListener(this);
        verify.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.google_login:


                signIn();
                break;
            case R.id.btnLogin:
                if (checkAndCall()) {
                    String inputEmail = email.getText().toString();
                    String inputPassword = password.getText().toString();
                    loginWihtEmailPassword(inputEmail, inputPassword);
                }
                break;
            case R.id.verify:
                verifyAccount();
                break;
        }
    }

    private void verifyAccount() {
        mAuth.getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(getActivity(), new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Verification email sent please verify email id before login", Toast.LENGTH_LONG).show();

                        } else {
                        }
                    }
                });
    }

    private boolean checkAndCall() {
        if (email == null || password == null) {
            SingleButtonError.with(getActivity()).setMessage("Something went wrong please try again").show();
            return false;
        } else if (email.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Email or password missing !", Toast.LENGTH_LONG).show();
            return false;
        } else if (password.getText().toString().length() <= 7) {
            Toast.makeText(getActivity(), "Invalid password", Toast.LENGTH_LONG).show();

            return false;
        } else if (!email.getText().toString().contains("@lnmiit.ac.in")) {
            Toast.makeText(getActivity(), "Please enter your LNMIIT domain id", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void signIn() {

        Intent signInIntent = mGoogleApiClient.getSignInIntent();
        progressDialog.show();
        startActivityForResult(signInIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                progressDialog.dismiss();
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

            } catch (ApiException e) {

            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        verify.setVisibility(View.GONE);
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        if(!acct.getEmail().contains("@lnmiit.ac.in")){
            revoke();
            return;
        }
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        progressDialog.show();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            progressDialog.dismiss();
                            assert user != null;
                            getUser(user);
                        } else {
                            progressDialog.dismiss();
                            SingleButtonError.with(getActivity())
                                    .setMessage(Objects
                                            .requireNonNull(task.getException())
                                            .getMessage())
                                    .hideHeading()
                                    .show();

                        }
                    }
                });
    }

    private void loginWihtEmailPassword(String email, String password) {
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
                            if (user.isEmailVerified()) {
                                getUser(user);
                            } else {
                                SingleButtonError.with(getActivity()).setMessage("Please verify email").show();
                                verify.setVisibility(View.VISIBLE);
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

    private void getUser(FirebaseUser user) {
        if (!user.isEmailVerified()) {
            SingleButtonError.with(getActivity()).setMessage("Please verify email").show();
            verify.setVisibility(View.VISIBLE);
            verifyAccount();
            return;
        }
        if(!user.getEmail().contains("@lnmiit.ac.in")){
            revoke();
            return;
        }
        progressDialog.dismiss();
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Constants.isLoggedIn, "true");
        editor.putString(Constants.loggedInUserId, user.getUid());
        editor.putString(Constants.loggedInFirstName, user.getDisplayName());
        editor.putString(Constants.loggedInEmail, user.getEmail());
        try{
            editor.putString(Constants.loggedInUserName,user.getDisplayName());
        }catch (Exception ignored){}
        //editor.putInt(Constants.loggedInUserType, user.getUserType());
        editor.putString(Constants.loggedInUserMobile, user.getPhoneNumber());
        //editor.putInt(Constants.loggedInUserSex, user.getUserSex());


        editor.apply();
        Intent intent = new Intent(getActivity(), SearchVehicleActivity.class);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    private void revoke() {
        mAuth.signOut();
        Toast.makeText(getActivity(),"Please login using your LNMIIT domain Id !", Toast.LENGTH_LONG).show();
        mGoogleApiClient.revokeAccess().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                signIn();
            }
        });
    }

}

