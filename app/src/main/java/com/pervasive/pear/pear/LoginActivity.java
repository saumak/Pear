package com.pervasive.pear.pear;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.AutoCompleteTextView;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import static android.provider.AlarmClock.EXTRA_MESSAGE;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private AutoCompleteTextView mEmailView;

    private EditText mPasswordView;
    private Button mButton;
    private FirebaseAuth mAuth;
    private Button mSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mButton = (Button)findViewById(R.id.email_sign_in_button);
        mPasswordView = (EditText) findViewById(R.id.password);
        mSignup = (Button) findViewById(R.id.log_sign_up);
        mAuth = FirebaseAuth.getInstance();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                //replace this code to register
                signIN(email,password);

            }
        });
        // Initialize Firebase Auth
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });



        }

        private void signIN(String email, String password){

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(LoginActivity.this,"and here again!", Toast.LENGTH_LONG).show();

                            if (task.isSuccessful()) {

                                Intent intent = new Intent(LoginActivity.this, DashBoard.class);


                                startActivity(intent);
                            } else {

                                Toast.makeText(LoginActivity.this,"Fail", Toast.LENGTH_LONG).show();
                            }


                        }


                    });
        }
    }
