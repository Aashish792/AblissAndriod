package com.example.dell.ablissadrad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
public class Bookshareview extends AppCompatActivity {

    private WebView mywebview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        mywebview = (WebView) findViewById(R.id.booksharewebview);
        mywebview.setWebViewClient(new WebViewClient());
        mywebview.loadUrl("https://www.bookshare.org/cms");
        WebSettings webSettings = mywebview.getSettings();
        webSettings.setJavaScriptEnabled(true);



    }


}

