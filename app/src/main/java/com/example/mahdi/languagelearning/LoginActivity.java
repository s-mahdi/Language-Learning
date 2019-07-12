package com.example.mahdi.languagelearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        emailEditText=findViewById(R.id.email_edittext);
        passwordEditText=findViewById(R.id.password_edittext);
    }
    public void register(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
    }
    public void login(View view){
        String email=emailEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        Loading loading = new Loading(this);
        Authentication authentication = new Authentication(LoginActivity.this,loading);
        authentication.execute(email,password);

    }
}
