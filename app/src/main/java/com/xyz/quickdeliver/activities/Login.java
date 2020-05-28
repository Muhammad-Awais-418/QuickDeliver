package com.xyz.quickdeliver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.xyz.quickdeliver.MainActivity;
import com.xyz.quickdeliver.R;


public class Login extends AppCompatActivity {

    private EditText email, pass;
    private Button btnlogin,btnSignUp;
    private final static int LOGIN_PERMISSION = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylogin);
        //Initialize
        email = (EditText) findViewById(R.id.emaill);
        pass = (EditText) findViewById(R.id.passwordd);
        btnlogin = (Button) findViewById(R.id.signin1);
        btnSignUp = (Button)findViewById(R.id.btn_signup);

        //SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Login.this, Signup.class);
                startActivity(it);
            }
        });

        //login
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        AuthUI.getInstance().createSignInIntentBuilder()
                        .setAllowNewEmailAccounts(true).build(),LOGIN_PERMISSION
                );
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_PERMISSION) {
            startNewActivty(resultCode, data);
        }
    }

    private void startNewActivty(int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            Intent i = new Intent(Login.this, ActiveUsers.class);
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show();
        }
    }
}
