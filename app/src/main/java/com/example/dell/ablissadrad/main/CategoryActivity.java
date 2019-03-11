package com.example.dell.ablissadrad.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.ablissadrad.Bookshareview;
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
    private TextView tvtitle,textViewno;
    Document_Adapter document_adapter;
    List<Documents> documentsList;
    RecyclerView recyclerViewinformation;
    TextView txtnavname,txtusername;
    Button buttonbookshare;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofcategory);

        buttonbookshare = (Button) findViewById(R.id.bookshare);


        tvtitle = (TextView) findViewById(R.id.txtviewtitle) ;
        textViewno = (TextView) findViewById(R.id.notext);
        recyclerViewinformation = findViewById(R.id.recyclerview_selected);

        final ProgressDialog progressDialog = new ProgressDialog(CategoryActivity.this);
        progressDialog.setTitle("Fetching Data");
        progressDialog.setMessage("Please wait while we fetch the data.");
        progressDialog.show();


        documentsList = new ArrayList<>();

        final Intent intent = getIntent();
        String Title = intent.getExtras().getString("CategoryTitle");


        String output = Title.toLowerCase();

         tvtitle.setText(Title);

         if (Title.equals("Visual Impairment")){
             buttonbookshare.setVisibility(View.VISIBLE);

              }
        buttonbookshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CategoryActivity.this, Bookshareview.class);
                startActivity(intent1);
            }
        });




        Call<List<Documents>> call = RetrofitClient.getmInstance().getApi().getdocs(output);

        call.enqueue(new Callback<List<Documents>>() {
            @Override
            public void onResponse(Call<List<Documents>> call, Response<List<Documents>> response) {

                progressDialog.dismiss();

                List<Documents> doc = response.body();
                Document_Adapter document_adapter = new Document_Adapter(doc,this,null);

                recyclerViewinformation.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewinformation.setAdapter(document_adapter);

//                if(doc.size()==0){
//
//                    textViewno.setVisibility(View.VISIBLE);
                //}


            }

            @Override
            public void onFailure(Call<List<Documents>> call, Throwable t) {

                Toast.makeText(CategoryActivity.this,"Errpr"+t,Toast.LENGTH_LONG).show();

            }
        });

    }

}
