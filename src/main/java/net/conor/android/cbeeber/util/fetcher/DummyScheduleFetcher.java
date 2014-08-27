package net.conor.android.cbeeber.util.fetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by keegac01 on 27/08/2014.
 */
public class DummyScheduleFetcher extends ScheduleFetcher{

    public String fetch(String feedUrl){
        InputStream inputStream = DummyScheduleFetcher.class.getResourceAsStream(feedUrl);
        StringBuffer buffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            return "";
        }
        return buffer.toString();
    }

}
