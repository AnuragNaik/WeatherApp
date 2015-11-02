package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment {
    private static final String LOG_TAG=DetailFragment.class.getSimpleName();
    private static final String FORECAST_SHARE_HASH_TAG=" #SunshineApp";
    private String mForecastStr;

    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent= getActivity().getIntent();
        if(intent!=null && intent.hasExtra(intent.EXTRA_TEXT)){
            mForecastStr= intent.getStringExtra(intent.EXTRA_TEXT);
            TextView textview= (TextView) rootView.findViewById(R.id.detail_text);
            textview.setText(mForecastStr);
        }
        return rootView;
    }

    public Intent createShareForecastIntent(){
        Intent shareIntent= new Intent(Intent.ACTION_SEND);
       //this flag will take back to the main activity who launches the Share task
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, mForecastStr+FORECAST_SHARE_HASH_TAG);
        return shareIntent;
    }

}
