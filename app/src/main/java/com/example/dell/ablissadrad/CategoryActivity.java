package com.example.dell.ablissadrad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {
    private TextView tvtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofcategory);

        tvtitle = (TextView) findViewById(R.id.txtviewtitle) ;

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("CategoryTitle");
        tvtitle.setText(Title);
    }
}
