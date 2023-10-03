package com.example.datamanagingfrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppSecurity extends AppCompatActivity implements View.OnClickListener {

    Button getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_security);
        getStarted = findViewById(R.id.getStarted);
        getStarted.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ActionPage.class);
        startActivity(intent);
    }
}