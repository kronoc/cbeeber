package net.conor.android.cbeeber.util.parser;

import net.conor.android.cbeeber.model.Schedule;
import net.conor.android.cbeeber.util.fetcher.DummyScheduleFetcher;
import net.conor.android.cbeeber.util.fetcher.ScheduleFetcher;

/**
 * Created by keegac01 on 21/08/2014.
 */
public class ScheduleProvider {

    private static final String SCHEDULE_URL = "http://open.live.bbc.co.uk/aps/cbeebies/programmes/schedules.xml";
    public static final String FALLBACK_SCHEDULES_XML = "schedules.xml";
    private ScheduleFetcher fetcher = new ScheduleFetcher();
    private ScheduleParser parser = new ScheduleParser();
    private Schedule schedule;

    public Schedule getSchedule() {
        try {
            String scheduleXML = fetcher.fetch(SCHEDULE_URL);
            schedule = parser.parse(scheduleXML);
        }catch(RuntimeException e) {
            if (schedule==null){
                DummyScheduleFetcher dummyScheduleFetcher = new DummyScheduleFetcher();
                return parser.parse(dummyScheduleFetcher.fetch(FALLBACK_SCHEDULES_XML));
            }
        }
        return schedule;
    }

}
