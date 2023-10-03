package com.example.datamanagingfrontend.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datamanagingfrontend.R;

public class ParticipantHolder extends RecyclerView.ViewHolder{

    TextView team,solved,time;

    public ParticipantHolder(@NonNull View itemView){
        super(itemView);
        team = itemView.findViewById(R.id.participantList_team);
        solved = itemView.findViewById(R.id.participantList_solved);
        time = itemView.findViewById(R.id.participantList_time);
    }
}