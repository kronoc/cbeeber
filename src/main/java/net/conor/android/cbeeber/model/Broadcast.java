package net.conor.android.cbeeber.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Root(strict = false)
public class Broadcast implements Serializable {
    private static final long serialVersionUID = 1l;

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private static final DateFormat timeFormat = new SimpleDateFormat("h:mm a");

    @Element
    private String pid;
    @Element
    private String start;
    @Element
    private String end;
    @Element
    private Integer duration;
    @Element
    private Programme programme;


    public String getImageUrl() {
        return tleo().getImageUrl();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Date getStart() {
        return parse(start);
    }

    public void setStart(Date start) {
        this.start = format(start);
    }

    public String getPrettyTime() {
        return timeFormat.format(parse(start));
    }

    private Date parse(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String format(Date date) {
        return dateFormat.format(date);
    }

    public Date getEnd() {
        return parse(end);
    }

    public void setEnd(Date end) {
        this.end = format(end);
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTitle() {
        String parentTitle = tleo().getTitle();
        if (!parentTitle.equals(programme.getTitle())) {
            return parentTitle + " : " + programme.getTitle();
        } else {
            return programme.getTitle();
        }

    }

    public void setTitle(String title) {
        programme.setTitle(title);
    }

    public String getSubtitle() {
        return programme.getDisplaySubtitle();
    }

    public void setSubtitle(String subtitle) {
        programme.setDisplaySubtitle(subtitle);
    }

    public String getShortSynopsis() {
        return programme.getShortSynopsis();
    }

    public void setShortSynopsis(String shortSynopsis) {
        programme.setShortSynopsis(shortSynopsis);
    }

    public String getImagePid() {
        return programme.getImagePid();
    }

    public void setImagePid(String imagePid) {
        programme.setImagePid(imagePid);
    }

    public String getBrandPid() {
        return tleo().getPid();
    }

    public void setBrandPid(String brandPid) {
        tleo().setPid(pid);
    }

    private Programme tleo() {
        Programme programme = this.programme;
        while (programme != null) {
            if (programme.getParentProgramme() != null) {
                programme = programme.getParentProgramme();
            } else {
                break;
            }
        }
        return programme;
    }

    @Override
    public String toString() {
        return "Broadcast{" +
                "pid='" + pid + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", duration=" + duration +
                ", programme=" + programme.toString() +
                ", title='" + getTitle() + '\'' +
                ", subtitle='" + getSubtitle() + '\'' +
                ", shortSynopsis='" + getShortSynopsis() + '\'' +
                ", imagePid='" + getImagePid() + '\'' +
                ", brandPid='" + getBrandPid() + '\'' +
                '}';
    }
}
