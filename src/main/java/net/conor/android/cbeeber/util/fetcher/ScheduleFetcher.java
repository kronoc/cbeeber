package net.conor.android.cbeeber.util.fetcher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by keegac01 on 20/08/2014.
 */
public class ScheduleFetcher {

    public String fetch(String feedUrl){
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(feedUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10 * 10000);
            httpURLConnection.setConnectTimeout(20 * 1000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e){
//            Log.e("cbeeber", "Exception", e);
                throw new RuntimeException(e);
        }
        return buffer.toString();

    }
}
