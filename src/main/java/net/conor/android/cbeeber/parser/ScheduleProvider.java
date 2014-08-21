package net.conor.android.cbeeber.parser;

import net.conor.android.cbeeber.model.Schedule;

/**
 * Created by keegac01 on 21/08/2014.
 */
public class ScheduleProvider {

    private static final String SCHEDULE_URL = "http://www.bbc.co.uk/cbeebies/programmes/schedules.xml";
    private ScheduleFetcher fetcher = new ScheduleFetcher();
    private ScheduleParser parser = new ScheduleParser();

    public Schedule getSchedule(){
        String scheduleXML = fetcher.fetch(SCHEDULE_URL);
        return parser.parse(scheduleXML);
    }

}
