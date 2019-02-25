package com.example.dell.ablissadrad.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dell.ablissadrad.R;
import com.example.dell.ablissadrad.Utilities.RetrofitClient;
import com.example.dell.ablissadrad.adapter.Document_Adapter;
import com.example.dell.ablissadrad.data.Documents;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    private TextView tvtitle;
    Document_Adapter document_adapter;
    List<Documents> documentsList;
    RecyclerView recyclerViewinformation;
    TextView txtnavname,txtusername;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofcategory);


        tvtitle = (TextView) findViewById(R.id.txtviewtitle) ;
        recyclerViewinformation = findViewById(R.id.recyclerview_selected);


        documentsList = new ArrayList<>();

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("CategoryTitle");


        String output = Title.replaceAll("\\s+","").toLowerCase();

          // Toast.makeText(CategoryActivity.this,"After Trim"+output,Toast.LENGTH_LONG).show();

       tvtitle.setText(Title);

        Call<List<Documents>> call = RetrofitClient.getmInstance().getApi().getdocs(output);

        call.enqueue(new Callback<List<Documents>>() {
            @Override
            public void onResponse(Call<List<Documents>> call, Response<List<Documents>> response) {
                List<Documents> doc = response.body();

                Document_Adapter document_adapter = new Document_Adapter(this,doc);
                recyclerViewinformation.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewinformation.setAdapter(document_adapter);

            }

            @Override
            public void onFailure(Call<List<Documents>> call, Throwable t) {

            }
        });









    }





}
