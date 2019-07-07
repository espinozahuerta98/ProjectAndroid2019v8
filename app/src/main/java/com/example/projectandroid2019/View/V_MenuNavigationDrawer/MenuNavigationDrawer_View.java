package com.example.projectandroid2019.View.V_MenuNavigationDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectandroid2019.View.More;
import com.example.projectandroid2019.View.V_Map.MapsActivity;
import com.example.projectandroid2019.Model.User_Model;
import com.example.projectandroid2019.R;
import com.example.projectandroid2019.View.Information;
import com.example.projectandroid2019.View.V_Drink.Drink_View;
import com.example.projectandroid2019.View.V_Food.Food_View;
import com.example.projectandroid2019.View.V_Login.Login_View;
import com.example.projectandroid2019.View.V_Order.Order_View;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuNavigationDrawer_View extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;

    private CardView comidas,bebidas,pedidos,map;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_navigation_drawer__view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Init firebase

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        currentUser = mAuth.getCurrentUser();

       /* mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        categories = database.getReference("categories");
        currentUser = mAuth.getCurrentUser();*/


      /*mRecyclerView = findViewById(R.id.pictureRecycler);
       mRecyclerView.setHasFixedSize(true);

       mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

       mFirebaseDatabase = FirebaseDatabase.getInstance();
       mRef = mFirebaseDatabase.getReference("Categorias");*/




        //FirebaseUser user = mAuth.getCurrentUser();
        //user.getDisplayName();
        //For GoogleSignIn
        //if(mAuth.getCurrentUser()!=null)
        //Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        updateNavHeader();

        /*
        comidas = (CardView) findViewById(R.id.comidas);
        bebidas = (CardView) findViewById(R.id.bebidas);
        pedidos = (CardView) findViewById(R.id.pedidos);
        map = (CardView) findViewById(R.id.map);

        comidas.setOnClickListener((View.OnClickListener) this);
        bebidas.setOnClickListener((View.OnClickListener) this);
        pedidos.setOnClickListener((View.OnClickListener) this);
        map.setOnClickListener((View.OnClickListener) this);*/
        welcomeMessage();
    }

    public void welcomeMessage(){

        mDatabase.child("Usuarios").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User_Model user_model = dataSnapshot.getValue(User_Model.class);

                Toast.makeText(MenuNavigationDrawer_View.this, "Bienvenido " +user_model.getName() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void goComidas(View view){
        Intent intent = new Intent(MenuNavigationDrawer_View.this, Food_View.class);
        startActivity(intent);
    }

    public void goBebidas(View view){
        Intent intent = new Intent(MenuNavigationDrawer_View.this, Drink_View.class);
        startActivity(intent);
    }

    public void goPedidos(View view){
        Intent intent = new Intent(MenuNavigationDrawer_View.this, Order_View.class);
        startActivity(intent);
    }

    public void goMap(View view){
        Intent intent = new Intent(MenuNavigationDrawer_View.this, MapsActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigation_drawer__view, menu);
        return true;
    }

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.nav_more) {
            Intent intent = new Intent(getApplicationContext(), More.class);
            startActivity(intent);
        } else if (id == R.id.nav_information) {

            Intent intent = new Intent(getApplicationContext(), Information.class);
            startActivity(intent);

        } else if (id == R.id.nav_signout) {
            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(getApplicationContext(), Login_View.class);
            startActivity(loginActivity);
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateNavHeader() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_username);
        TextView navUserMail = headerView.findViewById(R.id.nav_user_mail);


       navUserMail.setText(currentUser.getEmail());
        navUsername.setText(currentUser.getDisplayName());

        // now we will use Glide to load user image
        // first we need to import the library

    }




}
