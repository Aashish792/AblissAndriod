package com.example.dell.ablissadrad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Document_Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_details);

//
        String title = getIntent().getExtras().getString("Information Title");

        String description = getIntent().getExtras().getString("Information Detail");

        TextView tvtitle = (TextView) findViewById(R.id.textviewdocumentscat);
        TextView textViewd = (TextView) findViewById(R.id.description);

        tvtitle.setText(title);
        textViewd.setText(description);
//



    }


}
