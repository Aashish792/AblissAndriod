package com.example.dell.ablissadrad;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.ablissadrad.adapter.Download;

//import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Document_Details extends AppCompatActivity {

    private Button downloadbutton;
    public static final String MESSAGE_PROGRESS = "message_progress";
    private static final int PERMISSION_REQUEST_CODE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_details);


        String title = getIntent().getExtras().getString("Information Title");

        String description = getIntent().getExtras().getString("Information Detail");

        TextView tvtitle = (TextView) findViewById(R.id.textviewdocumentscat);
        TextView textViewd = (TextView) findViewById(R.id.description);

        tvtitle.setText(title);
        textViewd.setText(description);

        ButterKnife.bind(this);

        downloadbutton = (Button) findViewById(R.id.downloaddocumentbutton);


        downloadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
                startActivity(i);

            }
        });

        registerReceiver();



    }



    private void startDownload(){

        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);

    }

    private void registerReceiver(){

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals(MESSAGE_PROGRESS)) {

                Download download = intent.getParcelableExtra("download");

            }
    };

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;
        }
    }

    private void requestPermission(){

        ActivityCompat.requestPermissions(Document_Details.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);

    }


    @OnClick(R.id.downloadromanigedocumentbutton)
        public void downloadFile(){

            if(checkPermission()){
                startDownload();
            } else {
                requestPermission();

            }
        }



    public void onRequestPxermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    startDownload();
                } else {

                    Snackbar.make(findViewById(R.id.coordinatorLayout),"Permission Denied, Please allow to proceed !", Snackbar.LENGTH_LONG).show();

                }
                break;
        }
    }



}


;


}
