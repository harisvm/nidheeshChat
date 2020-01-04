package com.ndk.gapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class OTP_Activity extends AppCompatActivity {
    EditText edt_otp1;
    String otp_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edt_otp1=findViewById(R.id.edt_otp);

                Intent intent=getIntent() ;
                String Otp= intent.getStringExtra("OTP");
                if(edt_otp1.getText().toString().length()>0) {
                    otp_string=edt_otp1.getText().toString();

                }
                    if (Otp ==otp_string) {
                        Intent intent1 = new Intent(OTP_Activity.this, LocationActivity.class);
                        startActivity(intent1);
                        finish();
                    }





    }

}
