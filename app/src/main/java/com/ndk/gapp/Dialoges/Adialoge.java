package com.ndk.gapp.Dialoges;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.ndk.gapp.R;

import java.util.zip.Deflater;


public class Adialoge extends Dialog implements View.OnClickListener {

    Activity context;
    String otp;
    EditText edtotp;
    private Button yes;
    private Button no;
    private View v;



    public Adialoge(@NonNull Activity context,String otp1) {
        super(context);
        this.context=context;
        this.otp=otp1;
        otp=edtotp.getText().toString();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.otp_dialog);
        init();
        otp=edtotp.getText().toString();

        yes.setOnClickListener(this);
        no.setOnClickListener((View.OnClickListener) this);

       


    }

    private void init() {
        edtotp=findViewById(R.id.edt_otp);
        yes = (Button) findViewById(R.id.btn_ok);
        no = (Button) findViewById(R.id.btn_cancel);
    }



    public void show() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                context.finish();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();  
    }
}
