package com.kortekslab.locationappmobil.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.kortekslab.locationappmobil.R;

public class LoginActivity extends AppCompatActivity {

    //private TextInputEditText textUsername,textPassword;
   // private static final Pattern PASSWORD_PATTERN= Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    private TextInputLayout textInputUsername, textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputUsername = findViewById(R.id.textInputUsername);
        textInputPassword = findViewById(R.id.textInputPassword);

        textInputUsername.setErrorEnabled(true);
        textInputPassword.setErrorEnabled(true);


    }

    private boolean validateUsername() {
        String username = textInputUsername.getEditText().getText().toString().trim();
        if (username.isEmpty()) {
            textInputUsername.setError("Lutfen Kullanıcı Adınızı Giriniz");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = textInputPassword.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            textInputPassword.setError("Lutfen Parolanızı Giriniz");
            return false;
        }/*else if(!PASSWORD_PATTERN.matcher(password).matches()){
            textInputPassword.setError("Lutfen Parolanızı Duzgun Giriniz");
            return false;
        }*/
        else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void signUp(View v) {
        if (!validateUsername() | !validatePassword()) {
            return;
        }
        Toast.makeText(this, "thanks", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(LoginActivity.this,AdminActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}



