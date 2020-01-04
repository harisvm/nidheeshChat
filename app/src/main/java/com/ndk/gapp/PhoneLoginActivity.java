package com.ndk.gapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.ndk.gapp.Dialoges.Adialoge;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.concurrent.TimeUnit;

public class PhoneLoginActivity extends AppCompatActivity {
    private Button phonelogin;
    DialogFragment alertDialogBuilder;
    private String mobno;
    private EditText edt_mobno;
    private String code;
    boolean rectvalue=false;
    private FirebaseAuth mAuth;
    //private CallbackManager mCallbackManager;
   // AlertDialog.Builder alertDialogBuilder;
   private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonelogin);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setBackgroundColor(R.color.colorotp);
        edt_mobno = findViewById(R.id.edt_mobno);
        phonelogin = findViewById(R.id.btn_sign_otpvalid);
        phonelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edt_password.setVisibility(View.GONE);
                //if(edt_email.getText().length()>1) {
                mobno = edt_mobno.getText().toString();

                sendVerificationCode(mobno);
                //String code = edt_password.getText().toString().trim();
                String otp="";
               // code =
                withEditText(code);


              /*  Adialoge alertDialogBuilder = new Adialoge(PhoneLoginActivity.this,otp);
                alertDialogBuilder.show();*/
                //verifyVerificationCode(code);




               // Intent i = new Intent(PhoneLoginActivity.this, OTP_Activity.class);
                //i.putExtra("OTP",code);
               // startActivity(i);
                //}


            }
        });



    }
   /* public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }*/
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks1);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks1 = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            code = phoneAuthCredential.getSmsCode();
            //Toast.makeText(PhoneLoginActivity.this, "Authentication Passed."+code,
            //Toast.LENGTH_SHORT).show();

            //sometime the code is not detected automatically

            if (code != null) {
                  //editTextCode.setText(code);
                //verifying the
                //Log.e(PhoneLoginActivity.this,"hjghg"+code);

                verifyVerificationCode(code);

            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(PhoneLoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
            mResendToken = forceResendingToken;
        }
    };
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success124455744");
                            Intent i = new Intent(PhoneLoginActivity.this, OTP_Activity.class);
                            i.putExtra("OTP",code);
                            startActivity(i);
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            // [START_EXCLUDE]
                            //updateUI(STATE_SIGNIN_SUCCESS, user);
                            // [END_EXCLUDE]
                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid

                            }

                        }
                    }
                });
    }

    private void verifyVerificationCode(String otp) {
        //creating the credential
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
            Toast.makeText(PhoneLoginActivity.this, "Authentication Passed."+otp,
                    Toast.LENGTH_SHORT).show();
            //signing the user
            rectvalue=true;
            signInWithPhoneAuthCredential(credential);

        } catch (Exception e) {
            e.printStackTrace();
            rectvalue=false;
        }


    }
    public void withEditText(final String code) {
// AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter OTP");
       // TextInputLayout
        final EditText input = new EditText(PhoneLoginActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
       // input.setText(code);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //i.putExtra("OTP",code);
                String otpverifiy=input.getText().toString();

                //Toast.makeText(getApplicationContext(), "Text entered is " + input.getText().toString(), Toast.LENGTH_SHORT).show();
                if(otpverifiy.equals(code)) {
                    FancyToast.makeText(PhoneLoginActivity.this,"Success ",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                    Intent i1 = new Intent(PhoneLoginActivity.this, LocationActivity.class);
                    startActivity(i1);
                }
                else {
                   /* Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    View layout = null;
                    toast.setView(layout);//setting the view of custom toast layout
                    toast.show();*/

                    FancyToast.makeText(PhoneLoginActivity.this,"Invalid otp ",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    //Toast.makeText(getApplicationContext(), "Invalid otp " , Toast.LENGTH_SHORT).show();

                }



            }
        });
        builder.setNegativeButton("Cancel" ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               return;
            }
        });


        //alertDialog = builder.create();
       builder.show();
    }

}
