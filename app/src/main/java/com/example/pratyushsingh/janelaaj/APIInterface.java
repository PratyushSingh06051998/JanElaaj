package com.example.pratyushsingh.janelaaj;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by pratyushsingh on 20/05/18.
 */

public interface APIInterface {

    @POST("/form")
    Call<Response1> getresponse1(@Body FormSendStructure formSendStructure);


    @GET("/all")
    Call<Response2> getresponse2();

    @GET("/del")
    Call<Response1> getdeldone();

}
