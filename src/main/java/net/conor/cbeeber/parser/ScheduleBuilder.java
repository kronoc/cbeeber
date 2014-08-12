package net.conor.cbeeber.parser;

import android.util.Log;
import android.util.Xml;
import net.conor.cbeeber.Schedule;
import net.conor.cbeeber.ScheduleItem;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ScheduleBuilder {
    private Schedule schedule;
    private ScheduleItem item;
    private ArrayList<ScheduleItem> items;
/*
    private boolean flagOfLastBuildDate = false;
    private String lastBuildDate = "";
    private boolean flagOfItem = false;
    private boolean flagOfTitle = false;
    private String title = "";
    private boolean flagOfDescription = false;
    private String description = "";
    private boolean flagOfGuid = false;
    private String guid = "";
    private boolean flagOfPubDate = false;
    private String pubDate = "";
*/

    public Schedule build(String url){
        Reader reader = fetchSchedule(url);
        XmlPullParser xmlPullParser = buildParser(reader);
        Schedule schedule = contructSchedule(xmlPullParser);
        return schedule;
    }

    private XmlPullParser buildParser(Reader reader){
        XmlPullParser xmlPullParser = Xml.newPullParser();
        try {
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, Boolean.TRUE);
            xmlPullParser.setInput(reader);
        } catch (XmlPullParserException e) {
            Log.e("cbeeber", "Exception", e);
        }
        return xmlPullParser;
    }

    private Reader fetchSchedule(String feedUrl){
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = null;
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream = null;
            url = new URL(feedUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10 * 10000);
            httpURLConnection.setConnectTimeout(20 * 1000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e){
            Log.e("cbeeber", "Exception", e);
        }
        return new StringReader(buffer.toString());

    }

    private Schedule contructSchedule(XmlPullParser xmlPullParser){
        return null;
    }
/*

                XmlPullParser xmlPullParser = Xml.newPullParser();
        try
        {

            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, Boolean.TRUE);
            xmlPullParser.setInput(inputStream, null);

            while(xmlPullParser.next()!=XmlPullParser.END_DOCUMENT)
            {
                switch(xmlPullParser.getEventType())
                {
                    case XmlPullParser.START_TAG :
                        this.meetStartElement(xmlPullParser);
                        break;
                    case XmlPullParser.TEXT :
                        this.meetTextElement(xmlPullParser);
                        break;
                    case XmlPullParser.END_TAG :
                        this.meetEndElement(xmlPullParser);
                        break;
                    default:
                        break;
                }
            }
        }
        catch(Exception exception)
        {
            Log.e("CBeeber", "Exception", exception);
        }
        finally
        {
            if(inputStream!=null)
            {
                try
                {
                    inputStream.close();
                }
                catch(Exception exception)
                {
                    Log.e("CBeeber", "Exception", exception);
                }
            }

            if(httpURLConnection!=null)
            {
                httpURLConnection.disconnect();
            }
        }
        return this.schedule;
    }

    private void meetStartElement(XmlPullParser xmlPullParser)
    {
        String tagName = xmlPullParser.getName();

        if(tagName.equals("channel"))
        {
            this.schedule = new Schedule();
            this.items = new ArrayList<SceduleItem>();
        }

        if(tagName.equals("lastBuildDate"))
        {
            this.flagOfLastBuildDate = true;
        }

        if(tagName.equals("item"))
        {
            this.flagOfItem = true;
            this.item = new BBCNewsRSSItem();
        }

        if(tagName.equals("title") && flagOfItem)
        {
            this.flagOfTitle = true;
        }

        if(tagName.equals("description") && flagOfItem)
        {
            this.flagOfDescription = true;
        }

        if(tagName.equals("guid") && flagOfItem)
        {
            this.flagOfGuid = true;
        }

        if(tagName.equals("pubDate") && flagOfItem)
        {
            this.flagOfPubDate = true;
        }

        if(tagName.equals("thumbnail") && flagOfItem)
        {
            if(xmlPullParser.getAttributeValue(null,"width").equals("144"))
            {
                this.item.setThumbnail(xmlPullParser.getAttributeValue(null,"url"));
            }
        }
    }

    private void meetTextElement(XmlPullParser xmlPullParser)
    {
        String text = xmlPullParser.getText();

        if(this.flagOfLastBuildDate)
        {
            this.lastBuildDate = text;
        }

        if(this.flagOfItem && this.flagOfTitle)
        {
            this.title = text;
        }

        if(this.flagOfItem && this.flagOfDescription)
        {
            this.description = text;
        }

        if(this.flagOfItem && this.flagOfGuid)
        {
            this.guid = text;
        }

        if(this.flagOfItem && this.flagOfPubDate)
        {
            this.pubDate = text;
        }
    }

    private void meetEndElement(XmlPullParser xmlPullParser)
    {
        String tagName = xmlPullParser.getName();

        if(tagName.equals("channel"))
        {
            this.bbcNewsRSS.setItems(this.items);
        }

        if(tagName.equals("lastBuildDate"))
        {
            this.flagOfLastBuildDate = false;
            this.bbcNewsRSS.setLastBuildDate(this.lastBuildDate);
        }

        if(tagName.equals("item"))
        {
            this.flagOfItem = false;
            this.items.add(item);
        }

        if(tagName.equals("title") && flagOfItem)
        {
            this.flagOfTitle = true;
            this.item.setTitle(this.title);
            this.item.setUid(String.valueOf(this.title.hashCode()));
        }

        if(tagName.equals("description") && flagOfItem)
        {
            this.flagOfDescription = true;
            this.item.setDescription(this.description);
        }

        if(tagName.equals("guid") && flagOfItem)
        {
            this.flagOfGuid = true;
            this.item.setGuid(this.guid);
        }

        if(tagName.equals("pubDate") && flagOfItem)
        {
            this.flagOfGuid = true;
            this.item.setPubDate(this.pubDate);
        }
    }*/
}
