package net.conor.android.cbeeber;

import net.conor.android.cbeeber.parser.ScheduleFetcher;
import net.conor.android.cbeeber.model.Schedule;
import net.conor.android.cbeeber.parser.ScheduleParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by keegac01 on 20/08/2014.
 */
public class ScheduleReaderRunner {

    private static final boolean offline = false;

    public static void main(String[] args) throws Exception {

        ScheduleParser reader = new ScheduleParser();
        String feed;
        if (offline) {

            InputStream inputStream = ScheduleReaderRunner.class.getResourceAsStream("../../../../schedules.xml");

            StringBuffer buffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
            feed = buffer.toString();
            System.out.println(feed);

        } else {
            ScheduleFetcher fetcher = new ScheduleFetcher();
            feed = fetcher.fetch("http://www.bbc.co.uk/cbeebies/programmes/schedules.xml");
        }
        Schedule schedule = reader.parse(feed);
        System.out.println(schedule.toString());
    }
}
