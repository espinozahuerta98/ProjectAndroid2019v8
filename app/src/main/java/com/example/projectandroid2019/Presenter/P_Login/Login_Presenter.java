package com.example.projectandroid2019.Presenter.P_Login;

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

public class Login_Presenter {

    private Context mContext;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String TAG = "Login_Presenter";

    public Login_Presenter(Context mContext, FirebaseAuth mAuth, DatabaseReference mDatabase) {
        this.mContext = mContext;
        this.mAuth = mAuth;
        this.mDatabase = mDatabase;
    }

    public void singInUser(String email, String password){

        final ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setMessage("Ingresando...");
        dialog.setCancelable(false);
        dialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            dialog.dismiss();
                            //mDatabase.child("Usuarios").setValue(task.getResult().getUser().getUid());
                            //mDatabase.child("Usuarios").child(task.getResult().getUser().getUid()).child("titulo").setValue("Texto");
                            //se puede colocar el tiempo

                            Intent intent = new Intent(mContext, MenuNavigationDrawer_View.class);
                            mContext.startActivity(intent);
                        } else {
                            dialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

}
