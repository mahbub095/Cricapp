package com.owntaks.mahbub.cricket.Activity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.owntaks.mahbub.cricket.Adapter.CricketMatchDataAdapter;
import com.owntaks.mahbub.cricket.Helper.CricketMatchDataLoader;
import com.owntaks.mahbub.cricket.Model.CricketMatchData;
import com.owntaks.mahbub.cricket.R;

import java.util.ArrayList;
import java.util.List;

public class HomeA extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<CricketMatchData>> {



private AdView mAdView;
private TextView mEmptyStateTextView;

private static final String CRICKET_MATCH_URL =
        "http://cricapi.com/api/matchCalendar?apikey=qYgXqPV0R3VqPHzKKA1nBMwQqsH2";

private static final int CRICKET_LOADER_ID = 1;
private CricketMatchDataAdapter mAdapter;

@Override
public Loader<List<CricketMatchData>> onCreateLoader(int id, Bundle args) {
        return new CricketMatchDataLoader(this, CRICKET_MATCH_URL);
        }

@Override
public void onLoadFinished(Loader<List<CricketMatchData>> loader, List<CricketMatchData> data) {

        View loadingIndicator = findViewById(R.id.progress_bar);
        loadingIndicator.setVisibility(View.GONE);

        mAdapter.clear();

        if (data != null && !data.isEmpty()) {
        mAdapter.addAll(data);
        }
        }

@Override
public void onLoaderReset(Loader<List<CricketMatchData>> loader) {
        mAdapter.clear();
        }

@Override
protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListView cricketListView = (ListView) findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        cricketListView.setEmptyView(mEmptyStateTextView);


        MobileAds.initialize(this, "ca-app-pub-4177685083716160/6438879522");
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        if(getSupportActionBar()!=null){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



final ArrayList<CricketMatchData> criketMatch = new ArrayList<CricketMatchData>();

        mAdapter = new CricketMatchDataAdapter(this, criketMatch);

        cricketListView.setAdapter(mAdapter);

        cricketListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CricketMatchData data = criketMatch.get(position);
        Toast toast = Toast.makeText(getApplicationContext(), data.getDate(), Toast.LENGTH_SHORT);
        toast.show();
        Intent score = new Intent(HomeA.this, Main2Activity.class).putExtra("unique_id", data.getUnique_id());
        score.putExtra("sdate", data.getDate());
        score.putExtra("title", data.getTitle());
        startActivity(score);

        }
        });

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
        android.app.LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(CRICKET_LOADER_ID, null, this);
        } else {
        View loadingIndicator = findViewById(R.id.progress_bar);
        loadingIndicator.setVisibility(View.GONE);

                mEmptyStateTextView.setText(R.string.no_internet);
        }
        }
@Override
public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home);
        finish();
        return super.onOptionsItemSelected(item);
        }
        }



