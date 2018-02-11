package com.owntaks.mahbub.cricket.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.owntaks.mahbub.cricket.R;


public class Score extends Fragment {

    private AdView mAdView;
    WebView wv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        this.wv = (WebView) v.findViewById(R.id.web1);
        this.wv.getSettings().setJavaScriptEnabled(true);
        this.wv.setFocusable(true);
        this.wv.setFocusableInTouchMode(true);
        this.wv.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // this.wv.getSettings().setCacheMode(2);
        if (Build.VERSION.SDK_INT >= 19) {
            this.wv.setLayerType(2, null);
        } else {
            this.wv.setLayerType(1, null);
        }
        this.wv.getSettings().setDomStorageEnabled(true);
        this.wv.getSettings().setDatabaseEnabled(true);
        this.wv.getSettings().setAppCacheEnabled(true);
        //   this.wv.setScrollBarStyle(0);
        this.wv.loadUrl("http://m.cricbuzz.com/cricket-match/live-scores");
        this.wv.setWebViewClient(new WebViewClient());

        MobileAds.initialize(getActivity(), "ca-app-pub-4177685083716160/6438879522");
        mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        return v;
    }
}
