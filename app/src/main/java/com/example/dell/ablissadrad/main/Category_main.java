package com.example.dell.ablissadrad.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.ablissadrad.data.Category;
import com.example.dell.ablissadrad.R;
import com.example.dell.ablissadrad.adapter.RecyclerViewAdapter;
import com.example.dell.ablissadrad.data.User;
import com.example.dell.ablissadrad.storage.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class Category_main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvtitle,txtnavname,txtusername;
    List<Category> lstcat;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvtitle = (TextView) findViewById(R.id.txtviewtitle) ;


//


        lstcat = new ArrayList<>();
        lstcat.add(new Category("Visual Impairment", "For those with visual aid",R.drawable.visually ));
        lstcat.add(new Category("Hearing Impairment","For those with hearing aid",R.drawable.hearing));
        lstcat.add(new Category("Physical Impairment","For those with physical aid",R.drawable.physical));
        lstcat.add(new Category("Speaking Impairment","For those who require speaking aid",R.drawable.speaking));
        lstcat.add(new Category("Mental Impairment","For those who require mental aid",R.drawable.physical));
        lstcat.add(new Category("Visual Impairment", "For those with visual aid",R.drawable.visually ));
        lstcat.add(new Category("Hearing Impairment","For those with hearing aid",R.drawable.hearing));
        lstcat.add(new Category("Physical Impairment","For those with physical aid",R.drawable.physical));
        lstcat.add(new Category("Speaking Impairment","For those who require speaking aid",R.drawable.speaking));
        lstcat.add(new Category("Mental Impairment","For those who require mental aid",R.drawable.physical));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapter myAdapeter = new RecyclerViewAdapter(this,lstcat);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapeter);




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
        getMenuInflater().inflate(R.menu.home, menu);
        txtnavname = findViewById(R.id.textviewnamenav);
        txtusername = findViewById(R.id.textViewusernamenav);
        User user = SharedPreferenceManager.getmInstance(this).getUser();
        String s = user.getName();
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
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
                Intent h= new Intent(Category_main.this,Home.class);
                startActivity(h);
                break;
            case R.id.nav_cat:
                Intent c= new Intent(Category_main.this,Category_main.class);
                startActivity(c);
                break;
            case R.id.nav_info:
                Intent i= new Intent(Category_main.this,Information.class);
                startActivity(i);
                break;
            case R.id.nav_forum:
                Intent f = new Intent(Category_main.this,Forum.class);
                startActivity(f);
                break;
//            case R.id.nav_setting:
//                Intent s = new Intent(Home.this,Tools.class);
//                startActivity(s);
//                break;
//            case R.id.nav_exit:
//                Intent t= new Intent(Home.this,Tools.class);
//                startActivity(t);
//                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
