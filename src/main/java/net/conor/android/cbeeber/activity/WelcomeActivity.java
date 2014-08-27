package net.conor.android.cbeeber.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import net.conor.android.cbeeber.R;
import net.conor.android.cbeeber.controller.RetrieveScheduleAsyncTask;
import net.conor.android.cbeeber.util.location.LocationFinder;

/**
 * Created by keegac01 on 02/07/2014.
 */
public class WelcomeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.setMargins(0, 0, 0, displayMetrics.heightPixels/8);

        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(layoutParams);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundResource(R.drawable.splash);
        relativeLayout.addView(progressBar);

        this.setContentView(relativeLayout);

        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isInternetConnected = networkInfo!=null ? networkInfo.isConnected() : false;
        if(!isInternetConnected)
        {
            new AlertDialog
                    .Builder(this, AlertDialog.THEME_HOLO_LIGHT)
                    .setTitle("No Internet Connection")
                    .setMessage("This app needs an internet connection to retrieve latest schedule.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    WelcomeActivity.this.finish();
                                }
                            }
                    ).show();
        }
        else
        {
            InfoBox.showInfo(this,"Good News, you have an internet connection");

            LocationFinder locationFinder = new LocationFinder((LocationManager) this.getSystemService(Context.LOCATION_SERVICE), new Geocoder(this));
            if (locationFinder.isUK()){
                InfoBox.showInfo(this,"You are in the UK - CBeebies Channel is available in your country.");
                RetrieveScheduleAsyncTask retrieveAsync = new RetrieveScheduleAsyncTask(this);
                retrieveAsync.execute();
            }else{
                new AlertDialog
                        .Builder(this, AlertDialog.THEME_HOLO_LIGHT)
                        .setTitle("Wrong Country")
                        .setMessage("Sorry, this app can only be used in the UK due to content rights restrictions.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        WelcomeActivity.this.finish();
                                    }
                                }
                        ).show();
            }
        }
    }



}