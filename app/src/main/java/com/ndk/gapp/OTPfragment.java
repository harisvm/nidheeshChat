package com.ndk.gapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;


import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class OTPfragment extends Fragment {

  /*  TextView txt_login, txt_newuser, txtUserName, txt_registration, txtPassword, txtLabelOTP, txt_header, txt_resendotp;
    AppCompatEditText edt_mobile, edt_confirmotp;
    Button btnsign;
    boolean isUser = false;
    Context context;
    public ProgressDialog pDialog;
    private static File file;
    LinearLayout linearMain, linearChild, linear, lyt_otp, lyt_mob, lyt_resendotp;
    private List<String> phoneContactsList = new ArrayList<String>();
    long rowContactId = 0;
    private long mLastClickTime = 0;
    public static final String whstasp = "whstasp";
    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;
    boolean otp = false;
    String phoneNumber = "", otpnum = "";
    FirebaseAuth auth;
    private String verificationCode;

    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;

    //The edittext to input the code

    //firebase auth object
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View fragmentview = inflater.inflate(R.layout.otp_fragment, container, false);

        context = getActivity();
        mAuth = FirebaseAuth.getInstance();
        edt_mobile = (AppCompatEditText) fragmentview.findViewById(R.id.edt_mob);
        edt_confirmotp = (AppCompatEditText) fragmentview.findViewById(R.id.edt_otp);
        //linear = (LinearLayout) fragmentview.findViewById(R.id.lyt_login);
       // lyt_otp = (LinearLayout) fragmentview.findViewById(R.id.lyt_otp);
        //lyt_mob = (LinearLayout) fragmentview.findViewById(R.id.lyt_mob);
       // lyt_resendotp = (LinearLayout) fragmentview.findViewById(R.id.lyt_resendotp);
        btnsign = (Button) fragmentview.findViewById(R.id.btn_otp);
        //txt_resendotp = (TextView) fragmentview.findViewById(R.id.edt_mob);
        //txt_login = (TextView) fragmentview.findViewById(R.id.edt_otp);
        //lyt_otp.setVisibility(View.GONE);
        ///lyt_resendotp.setVisibility(View.GONE);
       *//* linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.hideKeyboard(v, context);
            }
        });*//*

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                if (Utility.HaveInternetConnection(context)) {
                    if (edt_mobile.getText().toString().length() == 10) {
                        if (otp) {

                            if (edt_confirmotp.getText().toString().length() > 0) {
                                otpnum = edt_confirmotp.getText().toString();
                                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otpnum);
                                SigninWithPhone(credential);
                            } else {
                                Utility.showToast(context, "Enter OTP");
                            }
                        } else {
                            if (edt_mobile.getText().toString().length() > 0) {
//                            UserLoginDetails(edttxt_username.getText().toString(),edttxt_password.getText().toString());
                                pDialog = ProgressDialog.show(getActivity(), "", "Please Wait...");
//                             UserLoginLogin(edttxt_username.getText().toString(), edttxt_password.getText().toString());
                                Utility.hideKeyboard(v, context);
                                phoneNumber = edt_mobile.getText().toString();
                                checkUserdetails(phoneNumber);

                            } else {
                                Utility.showToast(context, "Enter Mobile Number");
                            }

                        }
                    } else {
                        Toast.makeText(context, "Enter valid Mobile Number", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Check Your Internet Connection", Toast.LENGTH_LONG).show();
                }
            }
        });
        txt_resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendVerificationCode(phoneNumber, mResendToken);
            }
        });
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return fragmentview;
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                getActivity(),               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);
        otp = true;// ForceResendingToken from callbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();
            pDialog.dismiss();

            lyt_otp.setVisibility(View.VISIBLE);
            lyt_mob.setVisibility(View.GONE);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            pDialog.dismiss();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            pDialog.dismiss();
            verificationCode = s;
            otp = true;
            mResendToken = forceResendingToken;
            lyt_otp.setVisibility(View.VISIBLE);
            lyt_mob.setVisibility(View.GONE);

        }
    };

    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity
//                            Intent intent = new Intent(VerifyPhoneActivity.this, ProfileActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }
                        }
                    }
                });
    }


    private void SigninWithPhone(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            otp = true;
                            txt_resendotp.setVisibility(View.VISIBLE);
                            //Globals.mobnumber = edt_mobile.getText().toString();
                            //ImageRegistrationFragment fragment = new ImageRegistrationFragment();
                            Bundle args = new Bundle();
                            args.putString("mob", phoneNumber);
                            //Common.setPhonenumber(phoneNumber);
                            //Utility.commitFragment(getActivity(), fragment, args, "ImageRegistrationFragment");
                        } else {
                            Toast.makeText(context, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void checkUserdetails(final String mob) {

        String url = context.getResources().getString(R.string.userlisturl);
        Ion.with(context)
                .load(url)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if (e == null) {
                            try {
                                pDialog.dismiss();
                                for (int i = 0; i < result.size(); i++) {
                                    if (!result.get(i).isJsonNull()) {
                                        JSONObject resultJsonobj = new JSONObject(result.get(i).toString());
                                        if (resultJsonobj.getString("phone").equals(mob)) {
                                            isUser = true;
                                        }
                                    }
                                }
                                if (!isUser) {
                                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                            "+91" + phoneNumber,                     // Phone number to verify
                                            60,                           // Timeout duration
                                            TimeUnit.SECONDS,                // Unit of timeout
                                            getActivity(),        // Activity (for callback binding)
                                            mCallbacks);

                                    lyt_resendotp.setVisibility(View.VISIBLE);
                                } else {
                                    final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(getActivity()).create();
                                    alertDialog.setCanceledOnTouchOutside(false);
                                    alertDialog.setCancelable(false);
//                   alertDialog.setMessage(jsonObj.getString("msg") + "");
                                    alertDialog.setMessage("Already User!\n\nPlease do login with your User ID");
                                    alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    //LoginFragment fragment = new LoginFragment();
                                                    Bundle data = new Bundle();
                                                    //Utility.commitFragment((Activity) getActivity(), fragment, data, "FAQFragment");

                                                }
                                            });

                                    alertDialog.show();
                                }


//                                getActivity().getSupportFragmentManager().popBackStack();
                            } catch (Exception ex) {
                                //e.printStackTrace();

                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                });



    }*/
}
