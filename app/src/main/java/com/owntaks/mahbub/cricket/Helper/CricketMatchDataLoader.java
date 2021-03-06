package com.owntaks.mahbub.cricket.Helper;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.owntaks.mahbub.cricket.Model.CricketMatchData;

import java.util.List;


public class CricketMatchDataLoader extends AsyncTaskLoader {

    private String mUrl;

    public CricketMatchDataLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public List<CricketMatchData> loadInBackground()  {
        if (mUrl == null) {
            return null;
        }

        List<CricketMatchData> cricketMatchDatas = QueryUtils.fetchCricketMatchData(mUrl);
        return cricketMatchDatas;
    }
}
