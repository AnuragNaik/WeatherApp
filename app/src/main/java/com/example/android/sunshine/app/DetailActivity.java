package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    private static final String LOG_TAG=DetailFragment.class.getSimpleName();
    private static final String FORECAST_SHARE_HASH_TAG=" #SunshineApp";
    private String mForecastStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detailfragment, menu);
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        MenuItem menuItem= menu.findItem(R.id.action_share);
        ShareActionProvider mShareActionProvider;
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        if(mShareActionProvider!=null){
            //create an object of other class and call the function from here itself
            //since method is on other file
            // DetailFragment detailFragment= new DetailFragment();
            mShareActionProvider.setShareIntent( createShareForecastIntent());
        }
        else{
            Log.d(LOG_TAG, "shareActionProvider is Null!!");
        }
        return true;
    }

    public Intent createShareForecastIntent(){
        Intent shareIntent= new Intent(Intent.ACTION_SEND);
        //this flag will take back to the main activity who launches the Share task
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        TextView text= (TextView) findViewById(R.id.detail_text);
        mForecastStr = text.getText().toString();
        shareIntent.putExtra(Intent.EXTRA_TEXT, mForecastStr + FORECAST_SHARE_HASH_TAG);
        return shareIntent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id ==R.id.action_settings) {
            Toast.makeText(this, "Settings Tapped", Toast.LENGTH_SHORT).show();
            //When setting option is tapped then making an intent to LAUNCH SettingActivity
            Intent intent= new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
