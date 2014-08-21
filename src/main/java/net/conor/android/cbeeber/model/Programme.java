package net.conor.android.cbeeber.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by keegac01 on 19/08/2014.
 */
@Root(strict = false)
public class Programme implements Serializable{

    private static final String PLACEHOLDER_IMAGE_URL = "http://ichef.bbci.co.uk/images/ic/192x108/?????.jpg";
    private static final String IMAGE_TEMPLATE = "http://ichef.bbci.co.uk/images/ic/192x108/%s.jpg";

    @Element(required = false, name = "programme")
    private Programme parentProgramme;
    @Attribute
    private String type;
    @Element
    private String pid;
    @Element(required = false)
    private Integer position;
    @Element(required = false)
    private String title;
    @Element(name = "short_synopsis", required = false)
    private String shortSynopsis;
    @Element(required = false)
    private Integer duration;
    @Element(name = "pid")
    @Path("image")
    private String imagePid;
    @Element(name = "title")
    @Path("display_titles")
    private String displayTitle;
    @Element(name = "subtitle")
    @Path("display_titles")
    private String displaySubtitle;
    @Element(required = false)
    private String firstBroadcast;


    public String getImageUrl(){
        return imagePid != null ? String.format(IMAGE_TEMPLATE, imagePid) : PLACEHOLDER_IMAGE_URL;
    }
    public Programme getParentProgramme() {
        return parentProgramme;
    }

    public void setParentProgramme(Programme parentProgramme) {
        this.parentProgramme = parentProgramme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortSynopsis() {
        return shortSynopsis;
    }

    public void setShortSynopsis(String shortSynopsis) {
        this.shortSynopsis = shortSynopsis;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getImagePid() {
        return imagePid;
    }

    public void setImagePid(String imagePid) {
        this.imagePid = imagePid;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getDisplaySubtitle() {
        return displaySubtitle;
    }

    public void setDisplaySubtitle(String displaySubtitle) {
        this.displaySubtitle = displaySubtitle;
    }

    public String getFirstBroadcast() {
        return firstBroadcast;
    }

    public void setFirstBroadcast(String firstBroadcast) {
        this.firstBroadcast = firstBroadcast;
    }

    public String getUrl() {
        return pid;
    }

    @Override
    public String toString() {
        return "Programme{" +
                "parentProgramme=" + parentProgramme +
                ", type='" + type + '\'' +
                ", pid='" + pid + '\'' +
                ", position=" + position +
                ", title='" + title + '\'' +
                ", shortSynopsis='" + shortSynopsis + '\'' +
                ", duration=" + duration +
                ", imagePid='" + imagePid + '\'' +
                ", displayTitle='" + displayTitle + '\'' +
                ", displaySubtitle='" + displaySubtitle + '\'' +
                ", firstBroadcast='" + firstBroadcast + '\'' +
                '}';
    }
}
