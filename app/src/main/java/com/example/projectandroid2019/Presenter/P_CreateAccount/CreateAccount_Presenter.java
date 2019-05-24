package com.example.projectandroid2019.Presenter.P_CreateAccount;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.projectandroid2019.View.V_MenuNavigationDrawer.MenuNavigationDrawer_View;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount_Presenter {

    private Context mContext;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String TAG = "CreateAccount_Presenter";

    public CreateAccount_Presenter(Context mContext, FirebaseAuth mAuth, DatabaseReference mDatabase) {
        this.mContext = mContext;
        this.mAuth = mAuth;
        this.mDatabase = mDatabase;
    }

    public void signUpUser(final String email, final String name, final String user_createaccount, String password_createaccount){
        final ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setMessage("Registrando Usuario");
        dialog.setCancelable(false);
        dialog.show();
        mAuth.createUserWithEmailAndPassword(email, password_createaccount)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            dialog.dismiss();
                            Map<String,Object> crearUsuario = new HashMap<>();
                            crearUsuario.put("name", name);
                            crearUsuario.put("user_createaccount", user_createaccount);
                            crearUsuario.put("email",email);
                            mDatabase.child("Usuarios").child(task.getResult().getUser().getUid()).updateChildren(crearUsuario);

                           Intent intent = new Intent(mContext, MenuNavigationDrawer_View.class);
                           mContext.startActivity(intent);

                        } else {
                            dialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
