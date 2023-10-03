package com.example.datamanagingfrontend.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datamanagingfrontend.R;
import com.example.datamanagingfrontend.model.Participant;

import java.util.List;

public class ParticipantAdapter extends RecyclerView.Adapter<ParticipantHolder>{

    private List<Participant> participantList;

    public ParticipantAdapter(List<Participant> participantList){
        this.participantList = participantList;
    }

    @NonNull
    @Override
    public ParticipantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_participants_item,parent,false);
        return new ParticipantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantHolder holder, int position){
        Participant participant = participantList.get(position);
        holder.team.setText(participant.getTeam());
        holder.solved.setText(String.valueOf(participant.getSolved()));
        holder.time.setText(String.valueOf(participant.getTime()));
    }

    @Override
    public int getItemCount(){
        return participantList.size();
    }
}
