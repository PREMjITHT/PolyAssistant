package com.example.premjith.polyassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

/**
 * Created by PREMjITH
 * Created on 09-Jun-17.
 */

public class PhoneAuthActivity extends AppCompatActivity implements
        View.OnClickListener {
ProgressBar pp;
    EditText mPhoneNumberField, mVerificationField,txtUniqueId,txtUsername;
    Button mStartButton, mVerifyButton, mResendButton;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerificationId,p;
int usNum=0,x;
    private static final String TAG = "PhoneAuthActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        pp=findViewById(R.id.load_bar);
        txtUsername=findViewById(R.id.txt_user_name);
        txtUniqueId=findViewById(R.id.txt_college_id);
        mPhoneNumberField =  findViewById(R.id.field_phone_number);
        mVerificationField = findViewById(R.id.field_verification_code);
        mStartButton =  findViewById(R.id.button_start_verification);
        mVerifyButton =  findViewById(R.id.button_verify_phone);
        mResendButton =  findViewById(R.id.button_resend);

        mStartButton.setOnClickListener(this);
        mVerifyButton.setOnClickListener(this);
        mResendButton.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    mPhoneNumberField.setError("Invalid phone number.");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);
                Toast.makeText(PhoneAuthActivity.this, "OTP sented...", Toast.LENGTH_SHORT).show();
                mVerificationId = verificationId;
                mResendToken = token;

            }
        };



        x=getIntent().getExtras().getInt("ad");
        Toast.makeText(this, "oncre auth x="+x, Toast.LENGTH_SHORT).show();




    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(PhoneAuthActivity.this, "signInWithCredential:success", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = task.getResult().getUser();
                            // Write a message to the database
                            if(mAuth!=null){
                                 p=mAuth.getCurrentUser().getPhoneNumber();
                            }
                            database = FirebaseDatabase.getInstance();

                            if (x==1) {


                                Toast.makeText(PhoneAuthActivity.this, "User=" + x, Toast.LENGTH_SHORT).show();
                                myRef = database.getReference(""+p).child("user");
                                myRef.setValue(""+x);
                                usNum=1;


                            }else {

                                myRef = database.getReference(""+p).child("user");
                                myRef.setValue(""+x);
                                usNum=2;
                            }

                             myRef = database.getReference(""+p).child("collegeID");
                            Toast.makeText(PhoneAuthActivity.this, ""+txtUniqueId.getText(), Toast.LENGTH_SHORT).show();

                            myRef.setValue(""+txtUniqueId.getText());



                            myRef = database.getReference(""+p).child("username");
                            Toast.makeText(PhoneAuthActivity.this, ""+txtUsername.getText(), Toast.LENGTH_SHORT).show();

                            myRef.setValue(""+txtUsername.getText());
                            x=getIntent().getExtras().getInt("ad");
                            Toast.makeText(PhoneAuthActivity.this, "phon auth x="+x, Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(PhoneAuthActivity.this, MainActivity.class));
                            Intent in=new Intent(getApplicationContext(),MainActivity.class);
                            in.putExtra("k",x);
                            Toast.makeText(PhoneAuthActivity.this, "usnum="+usNum, Toast.LENGTH_SHORT).show();
                            startActivity(in);
                            finish();
                            Toast.makeText(PhoneAuthActivity.this, ""+user, Toast.LENGTH_SHORT).show();




                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                mVerificationField.setError("Invalid code.");
                            }
                        }
                    }
                });
    }


    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    private boolean validatePhoneNumber() {
        String phoneNumber = mPhoneNumberField.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberField.setError("Invalid phone number.");
            return false;
        }
        return true;
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            x=getIntent().getExtras().getInt("ad");
            Toast.makeText(this, "U are a user", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "x="+x, Toast.LENGTH_SHORT).show();
            Intent in=new Intent(this,MainActivity.class);
            in.putExtra("k",x);
            startActivity(in);
            //startActivity(new Intent(PhoneAuthActivity.this,StudentSearch.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_start_verification:
                if (!validatePhoneNumber()) {
                    return;
                }
                startPhoneNumberVerification(mPhoneNumberField.getText().toString());
                break;
            case R.id.button_verify_phone:
                String code = mVerificationField.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    mVerificationField.setError("Cannot be empty.");
                    return;
                }

                verifyPhoneNumberWithCode(mVerificationId, code);
                break;
            case R.id.button_resend:
                resendVerificationCode(mPhoneNumberField.getText().toString(), mResendToken);
                break;
        }



    }




}
