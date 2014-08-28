package net.conor.android.cbeeber.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Root(strict = false)
public class Schedule implements Serializable {
    private static final long serialVersionUID = 1l;
    @Attribute(name = "key")
    @Path("service")
    private String serviceKey;

    @Attribute(name = "date")
    @Path("day")
    private Date day;

    @ElementList(name = "broadcasts")
    @Path("day")
    private List<Broadcast> broadcasts;


    public List<Broadcast> getBroadcasts() {
        return broadcasts;
    }

    public void setBroadcasts(List<Broadcast> broadcasts) {
        this.broadcasts = broadcasts;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }


    @Override
    public String toString() {
        return "Schedule{" +
                "serviceKey='" + serviceKey + '\'' +
                ", day=" + day +
                ", broadcasts=" + broadcasts +
                '}';
    }

    public int size() {
        return broadcasts.size();
    }

    public int currentBroadcastIndex(){

        Calendar now = Calendar.getInstance();
        Calendar broadcastStart = Calendar.getInstance();
        Calendar broadcastEnd = Calendar.getInstance();
        int i = 0;
        for(Broadcast broadcast : broadcasts){
            broadcastStart.setTime( broadcast.getStart());
            broadcastEnd.setTime( broadcast.getEnd());
            if(broadcastStart.before(now) && broadcastEnd.after(now)) {
                break;
            }
            i++;
        }
        return i;


    }
}
