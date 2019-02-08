package com.example.dell.ablissadrad.Utilities;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    @FormUrlEncoded
    @POST("users/")
    Call<ResponseBody> createruser(
            @Field("name") String name,
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("Validusers/")
    Call<ResponseBody> Validateuser(
            @Field("username") String username,
            @Field("password") String password
    );



}
