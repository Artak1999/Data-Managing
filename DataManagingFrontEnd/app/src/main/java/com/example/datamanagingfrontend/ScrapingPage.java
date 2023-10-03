package com.example.datamanagingfrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ScrapingPage extends AppCompatActivity {

    TextView contentFromActionPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scraping_page);
        contentFromActionPage = findViewById(R.id.textView4);
        contentFromActionPage.setMovementMethod(new ScrollingMovementMethod());
        Intent intent = getIntent();
        String str = intent.getStringExtra("message1");
        contentFromActionPage.setText(str);
    }

}