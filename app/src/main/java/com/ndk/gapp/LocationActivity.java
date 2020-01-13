package com.ndk.gapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;


public class LocationActivity extends AppCompatActivity {
    AppCompatSpinner spinner;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseStorage mFirebaseStorage;
    List<Model_Location> td;
    private FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthstateListener;
    private DatabaseReference mLocationDbReference;
    private ChildEventListener mChildEventListener;

    Model_Location LocationInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mfirebaseAuth = FirebaseAuth.getInstance();

        spinner = (AppCompatSpinner) findViewById(R.id.Spnr_location);

        // Spinner click listener
        //spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        mFirebaseStorage = FirebaseStorage.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mLocationDbReference = mFirebaseDatabase.getReference().child("db_location");

        td = new ArrayList<Model_Location>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("db_Location");
        myRef.child("Id").setValue(1);
        myRef.child("Location").setValue("White Field");
        myRef.child("latitude").setValue("12.9698");
        myRef.child("logitude").setValue("77.7500");
        // myRef.child("Name").setValue("Nidheesh");
    }
}