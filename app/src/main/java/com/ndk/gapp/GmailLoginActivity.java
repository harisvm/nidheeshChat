package com.ndk.gapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GmailLoginActivity extends AppCompatActivity {

	TextInputEditText edt_email, edt_password;
	private String password;
	private String email;
	private Button signInButton, fb, phonelogin;

	private FirebaseAuth mAuth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_gmail);
		final Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		// toolbar.setBackgroundColor(R.color.colorred);

		edt_email = findViewById(R.id.edt_email);
		signInButton = findViewById(R.id.btn_Login_gmail);
		//fbButton=(LoginButton) findViewById(R.id.btn_fb_login);
		edt_password = findViewById(R.id.edt_password);

		mAuth = FirebaseAuth.getInstance();

		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		// FacebookSdk.sdkInitialize(this.getApplicationContext());
		if (user != null) {
			// Name, email address, and profile photo Url
			String name = user.getDisplayName();
			String email = user.getEmail();
			Uri photoUrl = user.getPhotoUrl();

			// Check if user's email is verified
			boolean emailVerified = user.isEmailVerified();

			// The user's ID, unique to the Firebase project. Do NOT use this value to
			// authenticate with your backend server, if you have one. Use
			// FirebaseUser.getIdToken() instead.
			String uid = user.getUid();
		}

		signInButton.setOnClickListener(new View.OnClickListener() {


			@SuppressLint("ResourceAsColor")
			@Override
			public void onClick(View v) {
				email = edt_email.getText().toString();
				password = edt_password.getText().toString();
				signin(email, password);
				// toolbar.setBackground(colorAccent).color.colorAccent);
			}


		});
	}

	public void signin(String eid, String pass) {
		mAuth.signInWithEmailAndPassword(eid, pass)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							// Sign in success, update UI with the signed-in user's information
							//Log.d(TAG, "signInWithEmail:success");
							FirebaseUser user = mAuth.getCurrentUser();

							Intent i = new Intent(GmailLoginActivity.this, LocationActivity.class);
							startActivity(i);

						} else {
							// If sign in fails, display a message to the user.
							signup(email, password);

						}


					}
				});

	}

	private void signup(String email, String password) {

		mAuth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							// Sign in success, update UI with the signed-in user's information
							//Log.d(TAG, "createUserWithEmail:success");
							FirebaseUser user = mAuth.getCurrentUser();
							//updateUI(user);
						} else {

						}

						// ...
					}
				});


	}


}
