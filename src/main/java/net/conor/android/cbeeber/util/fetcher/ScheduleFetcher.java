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
    private static final String USER_AGENT="info@kronoc.com CBeeber/1.0 (Android) ";

    public String fetch(final String feedUrl) {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(feedUrl);
            HttpURLConnection fetcherConnection = (HttpURLConnection) url.openConnection();
            fetcherConnection.setRequestProperty("User-Agent", USER_AGENT);
            fetcherConnection.setReadTimeout(10 * 10000);
            fetcherConnection.setConnectTimeout(20 * 1000);
            fetcherConnection.setRequestMethod("GET");
            fetcherConnection.setDoInput(true);
            fetcherConnection.connect();

            InputStream inputStream = fetcherConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            inputStream.close();
            fetcherConnection.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return buffer.toString();
    }
}
