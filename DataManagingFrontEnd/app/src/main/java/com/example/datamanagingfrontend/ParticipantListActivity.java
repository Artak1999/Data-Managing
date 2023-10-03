package com.example.datamanagingfrontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.datamanagingfrontend.adapter.ParticipantAdapter;
import com.example.datamanagingfrontend.model.Participant;
import com.example.datamanagingfrontend.retrofit.ParticipantApi;
import com.example.datamanagingfrontend.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParticipantListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_list);
        recyclerView = findViewById(R.id.participantList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RetrofitService retrofitService = new RetrofitService();
        ParticipantApi participantApi = retrofitService.getRetrofit().create(ParticipantApi.class);
        participantApi.getAllParticipants()
                .enqueue(new Callback<List<Participant>>() {
                    @Override
                    public void onResponse(Call<List<Participant>> call, Response<List<Participant>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Participant>> call, Throwable t) {
                        Toast.makeText(ParticipantListActivity.this, "Failed to load participants", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateListView(List<Participant> participantList){
        ParticipantAdapter participantAdapter = new ParticipantAdapter(participantList);
        recyclerView.setAdapter(participantAdapter);
    }
}