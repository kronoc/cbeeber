package net.conor.cbeeber;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

/**
 * Created by keegac01 on 02/07/2014.
 */
public class SplashActivity extends Activity {
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
                                    SplashActivity.this.finish();
                                }
                            }
                    ).show();
        }
        else
        {
            RetrieveScheduleAsyncTask retrieveAsync = new RetrieveScheduleAsyncTask(this);
            retrieveAsync.execute("http://bleb.org/tv/data/rss.php?ch=bbc4");
        }
    }

}