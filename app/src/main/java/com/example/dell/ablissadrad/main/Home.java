package com.example.dell.ablissadrad.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.ablissadrad.adapter.RecycleViewHomeAdapter;
import com.example.dell.ablissadrad.data.Home_Data;
import com.example.dell.ablissadrad.R;
import com.example.dell.ablissadrad.data.User;
import com.example.dell.ablissadrad.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtnavname,txtusername;

    List<Home_Data> lsthome;

    Toolbar toolbar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lsthome = new ArrayList<>();
        lsthome.add(new Home_Data("Categories","Click here to list all the differently abled category",R.drawable.cat_white));
        lsthome.add(new Home_Data("Information","Click here to list all the information",R.drawable.info_white));
        lsthome.add(new Home_Data("Forum","Click here to add question and get answers",R.drawable.forum_white));

        RecyclerView myhrv = (RecyclerView) findViewById(R.id.recycleviewhome);
        RecycleViewHomeAdapter myhAdapter = new RecycleViewHomeAdapter(this,lsthome);
        myhrv.setLayoutManager(new GridLayoutManager(this,1));
        myhrv.setAdapter(myhAdapter);
//


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
//
    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPreferenceManager.getmInstance(this).isLoggedIn()){

            Intent intent = new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        txtnavname = findViewById(R.id.textviewnamenav);
        txtusername = findViewById(R.id.textViewusernamenav);
        User user = SharedPreferenceManager.getmInstance(this).getUser();
        txtusername.setText(user.getName());
        txtnavname.setText(user.getUsername());
        return false;
    }

    @Override
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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the main place where we need to work on.
        int id=item.getItemId();
        switch (id){

            case R.id.nav_home:
                Intent h= new Intent(Home.this,Home.class);
                startActivity(h);
                break;
            case R.id.nav_cat:
                Intent c= new Intent(Home.this,Category_main.class);
                startActivity(c);
                break;
            case R.id.nav_info:
                Intent i= new Intent(Home.this,Information.class);
                startActivity(i);
                break;
            case R.id.nav_forum:
                Intent f = new Intent(Home.this,Forum.class);
                startActivity(f);
                break;

            case R.id.nav_Logout:
            SharedPreferenceManager.getmInstance(getApplicationContext()).clear();
            Intent t= new Intent(Home.this,Login.class);
            Toast.makeText(Home.this,"Logout Sucessful",Toast.LENGTH_LONG).show();
            startActivity(t);
                break;

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
