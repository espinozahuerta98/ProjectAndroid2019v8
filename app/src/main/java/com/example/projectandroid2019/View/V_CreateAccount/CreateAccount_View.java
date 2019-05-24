package com.example.projectandroid2019.View.V_CreateAccount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectandroid2019.Presenter.P_CreateAccount.CreateAccount_Presenter;
import com.example.projectandroid2019.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount_View extends AppCompatActivity implements View.OnClickListener {

    private EditText txt_email, txt_nombre, txt_usuario, txt_password,txt_confirmpassword;
    private CreateAccount_Presenter createAccount_presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account__view);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        showToolbar(getResources().getString(R.string.toolbar_tittle_createaccount), true);
        createAccount_presenter = new CreateAccount_Presenter(this,mAuth,mDatabase);
        txt_email = findViewById(R.id.email);
        txt_nombre = findViewById(R.id.name);
        txt_usuario = findViewById(R.id.user);
        txt_password = findViewById(R.id.password_createaccount);
        txt_confirmpassword = findViewById(R.id.confirmPassword);


        Button btn_register = findViewById(R.id.joinUs);
        btn_register.setOnClickListener(this);
    }
    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.joinUs:
                String email = txt_email.getText().toString().trim();
                String nombre = txt_nombre.getText().toString().trim();
                String usuario = txt_usuario.getText().toString().trim();
                String password = txt_password.getText().toString().trim();
                String confirmpassword = txt_confirmpassword.getText().toString().trim();

                if (password.equals(confirmpassword)) {
                    createAccount_presenter.signUpUser(email,nombre,usuario,password);

                }else{
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
                break;


        }



    }
}
