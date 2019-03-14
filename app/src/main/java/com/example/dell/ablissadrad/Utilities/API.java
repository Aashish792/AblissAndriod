package com.example.dell.ablissadrad.Utilities;

import com.example.dell.ablissadrad.data.Documents;
//import com.example.dell.ablissadrad.data.Documents_List;
import com.example.dell.ablissadrad.data.LoginResponse;

import java.util.List;


import retrofit2.http.Streaming;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface API {


    @FormUrlEncoded
    @POST("api/users/")
    Call<ResponseBody> createruser(
            @Field("name") String name,
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api/Validusers/")
    Call<LoginResponse> Validateuser(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("api/documentation")
    Call<List<Documents>> getDocuments();

    @GET("api/search/{category}")
    Call<List<Documents>> getdocs(@Path("category") String title);

    @GET("api/doc_category/{category}")
    Call<List<Documents>> getdocuments(@Path("category")String title);

    @Streaming
    @GET("media/documents/IMG_4834.PNG")
    Call<ResponseBody> downloadFile();

//    @GET
//    Call<ResponseBody> downloadFile(@Url String url);
}
