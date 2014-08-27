package net.conor.android.cbeeber.activity;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by keegac01 on 27/08/2014.
 */
public class InfoBox {

    public final static void showInfo(Context context, CharSequence text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
