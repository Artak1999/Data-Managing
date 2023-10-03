package com.example.datamanagingfrontend.retrofit;

import com.example.datamanagingfrontend.model.Participant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ParticipantApi{
    @GET("/participant/get-all")
    Call<List<Participant>> getAllParticipants();

    @POST("/participant/save")
    Call<Participant> save(@Body Participant participant);
}
