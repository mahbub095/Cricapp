package com.owntaks.mahbub.cricket.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.owntaks.mahbub.cricket.Helper.QueryUtils;
import com.owntaks.mahbub.cricket.Model.CricketScoreData;
import com.owntaks.mahbub.cricket.R;

public class Main2Activity extends AppCompatActivity {

    private AdView mAdView;


    private static final String CRICKET_SCORE_URL =
            "http://cricapi.com/api/cricketScore?apikey=qYgXqPV0R3VqPHzKKA1nBMwQqsH2&unique_id=";

    String s;
    String sdate;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        MobileAds.initialize(this, "ca-app-pub-4177685083716160/6438879522");
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        s = getIntent().getStringExtra("unique_id");
        sdate = getIntent().getStringExtra("sdate");
        title = getIntent().getStringExtra("title");
        String url = CRICKET_SCORE_URL + s;
        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setText("Match Title : " + " " + title);
        //   TextView scoreView = (TextView) findViewById(R.id.score);
        //  TextView typeView = (TextView) findViewById(R.id.type);
        TextView dateView = (TextView) findViewById(R.id.date);
        dateView.setText("DATE :" + " " + sdate);
        //   scoreView.setText("Score: "+" "+"Loading... or Match not yet Started");
        // typeView.setText("Match Type:Loading or Match not yet Started");
        CricketScoreAsync task = new CricketScoreAsync();
        task.execute(url);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    private class CricketScoreAsync extends AsyncTask<String, Void, CricketScoreData> {

        @Override
        protected CricketScoreData doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            CricketScoreData result = QueryUtils.fetchCricketScoreData(urls[0]);
            return result;
        }


        @Override
        protected void onPostExecute(CricketScoreData result) {
            if (result == null) {
                return;
            }

            updateUi(result);
        }
    }

    void updateUi(CricketScoreData data) {

        TextView date = (TextView) findViewById(R.id.date);
        date.setText("Date&Time :" + " " + data.getmDatetimeGmt());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home);
        finish();
        return super.onOptionsItemSelected(item);
    }
}