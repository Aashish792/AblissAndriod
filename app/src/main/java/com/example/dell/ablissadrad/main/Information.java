package com.example.dell.ablissadrad.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.ablissadrad.R;
import com.example.dell.ablissadrad.Utilities.RetrofitClient;
import com.example.dell.ablissadrad.adapter.Document_Adapter;
import com.example.dell.ablissadrad.data.Documents;
import com.example.dell.ablissadrad.data.User;
import com.example.dell.ablissadrad.storage.SharedPreferenceManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Information extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Document_Adapter document_adapter;
    List<Documents> documentsList;
    RecyclerView recyclerViewinformation;
    TextView txtnavname,txtusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ProgressDialog progressDialog = new ProgressDialog(Information.this);
        progressDialog.setTitle("Fetching Data");
        progressDialog.setMessage("Please wait while we fetch the data.");
        progressDialog.show();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerViewinformation = findViewById(R.id.recyclerview_information);

        documentsList = new ArrayList<>();

        try {
            ServerSocket socket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Call<List<Documents>> calldocuments = RetrofitClient.getmInstance().getApi().getDocuments();

        calldocuments.enqueue(new Callback<List<Documents>>() {
            @Override
            public void onResponse(Call<List<Documents>> call, Response<List<Documents>> response) {
                progressDialog.dismiss();
//



                List<Documents> docs = response.body();


                for (Documents h: docs){
                    Log.d("name",h.getTitle());
                    Log.d("bio",h.getDescription());
                }

                Document_Adapter document_adapter = new Document_Adapter(this,docs);
                recyclerViewinformation.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewinformation.setAdapter(document_adapter);




            }

            @Override
            public void onFailure(Call<List<Documents>> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(Information.this,"NOt Working"+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


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
        int id=item.getItemId();
        switch (id){

            case R.id.nav_home:
                Intent h= new Intent(Information.this,Home.class);
                startActivity(h);
                break;
            case R.id.nav_cat:
                Intent c= new Intent(Information.this,Category_main.class);
                startActivity(c);
                break;
            case R.id.nav_info:
                Intent i= new Intent(Information.this,Information.class);
                startActivity(i);
                break;
            case R.id.nav_forum:
                Intent f = new Intent(Information.this,Forum.class);
                startActivity(f);
                break;
//            case R.id.nav_setting:
//                Intent s = new Intent(Home.this,Tools.class);
//                startActivity(s);
//                break;
            case R.id.nav_Logout:
                SharedPreferenceManager.getmInstance(getApplicationContext()).clear();
                Intent t= new Intent(Information.this,Login.class);
                Toast.makeText(Information.this,"Logout Sucessful",Toast.LENGTH_LONG).show();
                startActivity(t);
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
