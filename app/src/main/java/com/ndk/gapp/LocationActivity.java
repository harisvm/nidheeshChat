package com.ndk.gapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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

/*

        int id=2;
        String Locname="Bangolore";
        String Loc="12.9716";

        String Logi="77.5946";
        String status="true";
*/


		addChildEventListener();
      /*  LocationInfo = new Model_Location(id, Locname,Loc,Logi,status);
        mLocationDbReference.push().setValue(LocationInfo);

       // mMessageEditText.setText("");
        mLocationDbReference.addChildEventListener(mChildEventListener);*/


		mAuthstateListener = new FirebaseAuth.AuthStateListener() {
			@Override
			public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


				FirebaseUser user = firebaseAuth.getCurrentUser();


				if (user != null) {
					//user is signedin

					//onSignedInInitializer(user.getDisplayName());
				} else {
					//user is signed out
					// Choose authentication providers
					//onSignedOutCleanUp();
                  /*  List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build());

                    // Create and launch sign-in intent
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .setIsSmartLockEnabled(false)
                                    .build(),
                            1);*/
				}


			}


		};


	}

	public void addChildEventListener() {


		mChildEventListener = new ChildEventListener() {
			private ArrayList<Model_Location> listaPedido;

			@Override
			public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

				//Model_Location friendlyMessage = dataSnapshot.getValue(Model_Location.class);
				//friendlyMessage.setId(dataSnapshot.getKey().indexOf(2));


/*
                for(DataSnapshot td1 :dataSnapshot.getChildren()){
                    Model_Location ml =new Model_Location();
                   //td.setId();
                    ml.setId(td1.getKey());
                    String loc= (String) td1.getValue();
                    //td= new ArrayList<Model_Location>();
                   // td.add(td1.getValue(Model_Location.class));

                  Toast.makeText(LocationActivity.this, ""+ml.getId(), Toast.LENGTH_SHORT).show();

                }*/
				//Toast.makeText(LocationActivity.this, "eddsddsdd"+friendlyMessage.getId(), Toast.LENGTH_SHORT).show();
				// Log.v("msggfhgfhfg",""+ td);


				for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
					//Log.v("Main",""+ childDataSnapshot.getKey().indexOf(1)); //displays the key for the node

					// td.add(td1.getValue(Model_Location.class));
					Model_Location ml = new Model_Location();
					//ml.setLocname(childDataSnapshot.getValue().toString());
					if (childDataSnapshot.getKey().equals("id")) {
						ml.setId(String.valueOf(childDataSnapshot.getValue()));
					} else if (childDataSnapshot.getKey().equals("locname")) {
						ml.setLocname(String.valueOf(childDataSnapshot.getValue()));
					} else if (childDataSnapshot.getKey().equals("log")) {
						ml.setLog(String.valueOf(childDataSnapshot.getValue()));
					}


					td.add(ml);


					Log.v("msg", "" + td);   //gives the value for given keyname
				}


				for (Model_Location td1 : td) {
					//Model_Location ml =(Model_Location) singleSnapshot.getValue();
					//ml.setId(singleSnapshot.getKey().indexOf(1));
					Toast.makeText(LocationActivity.this, "" + td1.getLocname(), Toast.LENGTH_SHORT).show();

				}
				//Toast.makeText(LocationActivity.this, ""+friendlyMessage, Toast.LENGTH_SHORT).show();
				// mMessageAdapter.add(friendlyMessage);
			}

			public ArrayList<Model_Location> getListaPedido() {
				return listaPedido;
			}

			@Override
			public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
				listaPedido = new ArrayList<Model_Location>();
				Model_Location p = dataSnapshot.getValue(Model_Location.class);
				listaPedido.add(p);

			}

			@Override
			public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

			}

			@Override
			public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}


		};

		mLocationDbReference.addChildEventListener(mChildEventListener);
	}
}
