package com.example.projectandroid2019.View.V_Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectandroid2019.Presenter.P_Login.Login_Presenter;
import com.example.projectandroid2019.R;
import com.example.projectandroid2019.View.V_CreateAccount.CreateAccount_View;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Login_View extends AppCompatActivity implements View.OnClickListener {

    private EditText txtEmail, txtPassword;
    private Login_Presenter login_presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        login_presenter = new Login_Presenter(this,mAuth,mDatabase);
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        Button mBtnLogin = findViewById(R.id.login);
        mBtnLogin.setOnClickListener(this);




    }
    public void goCreate_Account(View view){
        Intent intent = new Intent(Login_View.this, CreateAccount_View.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login:
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                login_presenter.singInUser(email,password);
                break;

        }

    }
}
