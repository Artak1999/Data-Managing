package com.example.datamanagingfrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datamanagingfrontend.model.Participant;
import com.example.datamanagingfrontend.retrofit.ParticipantApi;
import com.example.datamanagingfrontend.retrofit.RetrofitService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActionPage extends AppCompatActivity implements View.OnClickListener {

    EditText websiteUrl;
    Button scan, scrapingFromICPC, showScrapingResult,showDiagram;
    String contentOfAnyWebsite;
    List<String> tasksNumber = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_page);
        websiteUrl = findViewById(R.id.url);
        showScrapingResult = findViewById(R.id.showScrapingResult);
        showDiagram = findViewById(R.id.showDiagram);
        showDiagram.setOnClickListener(this);
        showScrapingResult.setOnClickListener(this);
        scan = findViewById(R.id.scan);
        scrapingFromICPC = findViewById(R.id.scraping);
        scan.setOnClickListener(this);
        scrapingFromICPC.setOnClickListener(this);
    }

    public class ScrapingFromAnyWebSite extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect(websiteUrl.getText().toString()).get();
                contentOfAnyWebsite = document.text();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(getApplicationContext(), ScrapingPage.class);
            intent.putExtra("message1", contentOfAnyWebsite);
            startActivity(intent);
        }
    }

    public class ScrapingFromICPC extends AsyncTask<Void, Void, Void> {
        String url = "https://neerc.ifmo.ru/archive/2021/standings.html";
        List<String> list = new ArrayList<>();
        RetrofitService retrofitService = new RetrofitService();
        ParticipantApi participantApi = retrofitService.getRetrofit().create(ParticipantApi.class);
        Participant participant = new Participant();

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                list.clear();
                Document document = Jsoup.connect(url).get();
                Element element = document.select("tbody").first();
                boolean end = false;
                int count = 0;
                for (Element tr : element.select("tr")) {
                    for (Element td : tr.select("td")) {
                        if(td.text().equals(".") || td.text().startsWith("+") || td.text().startsWith("-")){
                            if (td.text().equals("Total runs")) {
                                end = true;
                                break;
                            }
                            tasksNumber.add(td.text());
                        }
                        if (!td.text().equals(".") && !td.text().equals("") && !td.text().startsWith("+") && !td.text().equals("G")
                                && !td.text().equals("S") && !td.text().equals("B") && !td.text().startsWith("ICPC") && !td.text().startsWith("-")) {
                            if (td.text().equals("Total runs")) {
                                end = true;
                                break;
                            }
                            /*if (count == 4) {
                                participant.setTeam(list.get(1));
                                participant.setSolved(Integer.parseInt(list.get(2)));
                                participant.setTime(Integer.parseInt(list.get(3)));
                                participantApi.save(participant)
                                        .enqueue(new Callback<Participant>() {
                                            @Override
                                            public void onResponse(Call<Participant> call, Response<Participant> response) {
                                                Toast.makeText(ActionPage.this, "", Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailure(Call<Participant> call, Throwable t) {
                                                Toast.makeText(ActionPage.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
                                                Logger.getLogger(ActionPage.class.getName()).log(Level.SEVERE, "Error occurred", t);
                                                System.exit(0);
                                            }
                                        });
                                list.clear();
                                count = 0;
                            }*/
                            count++;
                            list.add(td.text());
                        }
                    }
                    if (end)
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == scan.getId()) {
            if (!URLUtil.isValidUrl(websiteUrl.getText().toString())) {
                Toast.makeText(ActionPage.this, "Error URL", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ActionPage.class);
                startActivity(intent);
            } else {
                new ScrapingFromAnyWebSite().execute();
                Intent intent = new Intent(getApplicationContext(), ScrapingPage.class);
                intent.putExtra("message1", contentOfAnyWebsite);
                startActivity(intent);
            }
        }
        if (view.getId() == scrapingFromICPC.getId()) {
            new ScrapingFromICPC().execute();
        }

        if (view.getId() == showScrapingResult.getId()) {
            Intent intent = new Intent(this, ParticipantListActivity.class);
            startActivity(intent);
        }
        if(view.getId() == showDiagram.getId()){
            Intent intent = new Intent(this,DiagramPage.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("list", (Serializable) tasksNumber);
            intent.putExtra("Bundle",bundle);
            startActivity(intent);
        }
    }
}