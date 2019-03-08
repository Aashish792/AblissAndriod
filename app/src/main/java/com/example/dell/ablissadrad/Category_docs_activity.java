package com.example.dell.ablissadrad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.ablissadrad.Utilities.RetrofitClient;
import com.example.dell.ablissadrad.adapter.Document_Adapter;
import com.example.dell.ablissadrad.data.Documents;
import com.example.dell.ablissadrad.main.CategoryActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category_docs_activity extends AppCompatActivity {
TextView textView,textViewno ;
Document_Adapter document_adapter;
List<Documents> documentsList;
RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_docs_activity);

        textView = (TextView) findViewById(R.id.txtviewdocscategorytitle);
        textViewno = (TextView) findViewById(R.id.notext);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_docscat);

        final ProgressDialog progressDialog = new ProgressDialog(Category_docs_activity.this);
        progressDialog.setTitle("Fetching Data");
        progressDialog.setMessage("Please wait while we fetch the data.");
        progressDialog.show();

        documentsList = new ArrayList<>();

        final Intent intent = getIntent();
        String data = intent.getExtras().getString("Docscattitle");
        textView.setText(data);

        Call<List<Documents>> call = RetrofitClient.getmInstance().getApi().getdocuments(data);

        call.enqueue(new Callback<List<Documents>>() {
            @Override
            public void onResponse(Call<List<Documents>> call, Response<List<Documents>> response) {
                progressDialog.dismiss();

                List<Documents> doc = response.body();
                Document_Adapter document_adapter = new Document_Adapter(doc,this,null);

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(document_adapter);

                if (doc.size()== 0){

                    textViewno.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onFailure(Call<List<Documents>> call, Throwable t) {
                Toast.makeText(Category_docs_activity.this,"Error"+t,Toast.LENGTH_LONG).show();

            }
        });



    }
}
