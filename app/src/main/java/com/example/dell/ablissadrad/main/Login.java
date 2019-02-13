package com.example.dell.ablissadrad.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.net.ServerSocket;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



import com.example.dell.ablissadrad.R;
import com.example.dell.ablissadrad.Utilities.RetrofitClient;
import com.example.dell.ablissadrad.data.LoginResponse;
import com.example.dell.ablissadrad.storage.SharedPreferenceManager;

public class Login extends AppCompatActivity {

    private EditText editTextpassword, editTextuname;
    private Button buttonLogin ,signup;
    private String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        try {
            ServerSocket socket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }

        editTextpassword = (EditText) findViewById(R.id.editTextpassword);
        editTextuname = (EditText) findViewById(R.id.editTextuname);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        signup = findViewById(R.id.buttonSignUphome);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPreferenceManager.getmInstance(this).isLoggedIn()){

            Intent intent = new Intent(Login.this, Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }

    public void userLogin() {

        String userid = editTextuname.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if (userid.isEmpty()){
            editTextuname.setError("Please Enter Username");
            editTextuname.requestFocus();
        }

        if (password.isEmpty()) {
            editTextpassword.setError("Password is empty");
            editTextpassword.requestFocus();
            return;
        }

        Call<LoginResponse> call = RetrofitClient.getmInstance().getApi().Validateuser(userid, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                String s = loginResponse.toString();
                Toast.makeText(Login.this,"Data is "+s,Toast.LENGTH_LONG).show();
                if (s.length()<=3){
                    editTextpassword.setError("User does not exists ");
                    Toast.makeText(Login.this,"Message"+loginResponse.getFields().getName(),Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    SharedPreferenceManager.getmInstance(Login.this).saveuser(loginResponse.getFields());
                        Intent intent = new Intent(Login.this, Home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this, "Error Message :" + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

////        call.enqueue(new Callback<ResponseBody>() {
////            @Override
////            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////
////
////                try {
////                    s = response.body().string();
//////                    System.out.println(s);
////
////
////                    if (s.length() <= 3) {
////                        editTextpassword.setError("Userid and password doesnot match");
////                    } else {
////
//////                        SharedPreferenceManager.getmInstance(Login.this).saveuser(s);
////                        Toast.makeText(Login.this,s,Toast.LENGTH_LONG).show();
////                        Intent intent = new Intent(Login.this, Home.class);
////                        startActivity(intent);
////
////
////                    }
//
//
////
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(Login.this, "Error Message :" + t.getMessage(), Toast.LENGTH_LONG).show();
//
//            }
//        });


    }

}






