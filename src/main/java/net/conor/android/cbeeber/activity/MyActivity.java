package net.conor.android.cbeeber.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import net.conor.android.cbeeber.controller.RetrieveScheduleAsyncTask;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                                    MyActivity.this.finish();
                                }
                            }
                    ).show();
        }
        else
        {
            RetrieveScheduleAsyncTask retrieveAsync = new RetrieveScheduleAsyncTask(this);
            retrieveAsync.execute();
        }
        //setContentView(R.layout.main);
    }
}
