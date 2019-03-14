package com.example.dell.ablissadrad;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.ablissadrad.Utilities.API;
import com.example.dell.ablissadrad.adapter.Download;

//import butterknife.BindView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;


public class Document_Details extends AppCompatActivity {

    //    private Button downloadbutton;
//    public static final String MESSAGE_PROGRESS = "message_progress";
    private static final int MY_PERMISSION_REQUEST = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_details);


        if (ContextCompat.checkSelfPermission(Document_Details.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Document_Details.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSION_REQUEST);
        }



        Button downloadButton = (Button) findViewById(R.id.downloadromanigedocumentbutton);

        Button downloadbutton1 = (Button) findViewById(R.id.downloaddocumentbutton);

        downloadbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i = new Intent();
                i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
                startActivity(i);

            }
        });




        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile();


            }
        });

        registerReceiver();

    }



    private void downloadFile() {

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.0.108:8000/");

        Retrofit retrofit = builder.build();

        API api = retrofit.create(API.class);

        Call<ResponseBody> call = api.downloadFile();

        call.enqueue(new Callback<ResponseBody>() {


            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                AsyncTask<Void, Void, Void>(){


                    @Override
                    protected Void doInBackground (Void...voids){

                        boolean success = writeResponseBodyToDisk(response.body());

                    }
                }.execute();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(Document_Details.this, "no ", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + "604_Aashish_K.C.pptx ");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("Abilis", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }


}












//        String title = getIntent().getExtras().getString("Information Title");
//
//        String description = getIntent().getExtras().getString("Information Detail");
//
//        TextView tvtitle = (TextView) findViewById(R.id.textviewdocumentscat);
//        TextView textViewd = (TextView) findViewById(R.id.description);
//
//        tvtitle.setText(title);
//        textViewd.setText(description);
//
//        ButterKnife.bind(this);
//
//        downloadbutton = (Button) findViewById(R.id.downloaddocumentbutton);
//
//
//        downloadbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//                Intent i = new Intent();
//                i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
//                startActivity(i);
//
//            }
//        });
//
//        registerReceiver();
//
//
//
//    }

//
//
//    private void startDownload(){
//
//        Intent intent = new Intent(this,DownloadService.class);
//        startService(intent);
//
//    }
//
//    private void registerReceiver(){
//
//        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(MESSAGE_PROGRESS);
//        bManager.registerReceiver(broadcastReceiver, intentFilter);
//
//    }
//
//    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
//            if(intent.getAction().equals(MESSAGE_PROGRESS)) {
//
//                Download download = intent.getParcelableExtra("download");
//
//            }
//    };
//
//    private boolean checkPermission(){
//        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (result == PackageManager.PERMISSION_GRANTED){
//
//            return true;
//
//        } else {
//
//            return false;
//        }
//    }
//
//    private void requestPermission(){
//
//        ActivityCompat.requestPermissions(Document_Details.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
//
//    }
//
//
//    @OnClick(R.id.downloadromanigedocumentbutton)
//        public void downloadFile(){
//
//            if(checkPermission()){
//                startDownload();
//            } else {
//                requestPermission();
//
//            }
//        }
//
//
//
//    public void onRequestPxermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    startDownload();
//                } else {
//
//                    Snackbar.make(findViewById(R.id.coordinatorLayout),"Permission Denied, Please allow to proceed !", Snackbar.LENGTH_LONG).show();
//
//                }
//                break;
//        }
//    }
//
//
//
//}
//
//
//;
//
//
//}
