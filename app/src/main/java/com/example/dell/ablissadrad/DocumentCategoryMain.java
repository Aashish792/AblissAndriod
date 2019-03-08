package com.example.dell.ablissadrad;

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
import android.widget.Toast;

import com.example.dell.ablissadrad.adapter.DoucmentCategory_Adapter;
import com.example.dell.ablissadrad.adapter.RecycleViewHomeAdapter;
import com.example.dell.ablissadrad.data.DocsCatData;
import com.example.dell.ablissadrad.data.Home_Data;
import com.example.dell.ablissadrad.main.Category_main;
import com.example.dell.ablissadrad.main.Forum;
import com.example.dell.ablissadrad.main.Home;
import com.example.dell.ablissadrad.main.Login;
import com.example.dell.ablissadrad.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class DocumentCategoryMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<DocsCatData> docsCatData;

    Toolbar toolbar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        docsCatData = new ArrayList<>();
        docsCatData.add(new DocsCatData("Education", "Click here to list all the education related document"));
        docsCatData.add(new DocsCatData("Allowance", "Click here to list all the allowance related document"));
        docsCatData.add(new DocsCatData("Government Related", "Click here to list all the document related document"));
        docsCatData.add(new DocsCatData("Miscellanous", "Click here to list all the miscellanous related document"));
        docsCatData.add(new DocsCatData("Acessories", "Click here to list all the acessories related documents"));

        RecyclerView myhrv = (RecyclerView) findViewById(R.id.recycleviewdocumentcat);
        DoucmentCategory_Adapter myhAdapter = new DoucmentCategory_Adapter(this, docsCatData);
        myhrv.setLayoutManager(new GridLayoutManager(this, 1));
        myhrv.setAdapter(myhAdapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.document_category, menu);
        return true;
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
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_home:
                Intent h = new Intent(DocumentCategoryMain.this, Home.class);
                startActivity(h);
                break;
            case R.id.nav_cat:
                Intent c = new Intent(DocumentCategoryMain.this, Category_main.class);
                startActivity(c);
                break;
            case R.id.nav_info:
                Intent i = new Intent(DocumentCategoryMain.this, DocumentCategoryMain.class);
                startActivity(i);
                break;
            case R.id.nav_forum:
                Intent f = new Intent(DocumentCategoryMain.this, Forum.class);
                startActivity(f);
                break;

            case R.id.nav_Logout:
                SharedPreferenceManager.getmInstance(getApplicationContext()).clear();
                Intent t = new Intent(DocumentCategoryMain.this, Login.class);
                Toast.makeText(DocumentCategoryMain.this, "Logout Sucessful", Toast.LENGTH_LONG).show();
                startActivity(t);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
