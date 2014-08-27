package net.conor.android.cbeeber.util.parser;

import net.conor.android.cbeeber.model.Schedule;
import net.conor.android.cbeeber.util.fetcher.DummyScheduleFetcher;
import net.conor.android.cbeeber.util.fetcher.ScheduleFetcher;

/**
 * Created by keegac01 on 21/08/2014.
 */
public class ScheduleProvider {

    private static final String SCHEDULE_URL = "http://www.bbc.co.uk/cbeebies/programmes/schedules.xml";
    private ScheduleFetcher fetcher = new ScheduleFetcher();
    private ScheduleParser parser = new ScheduleParser();

    public Schedule getSchedule(){
        String scheduleXML;
        try {
            scheduleXML = fetcher.fetch(SCHEDULE_URL);
        }catch(RuntimeException e){
           DummyScheduleFetcher dummyScheduleFetcher = new DummyScheduleFetcher();
           scheduleXML = dummyScheduleFetcher.fetch("schedules.xml");
        }

        return parser.parse(scheduleXML);
    }

}
