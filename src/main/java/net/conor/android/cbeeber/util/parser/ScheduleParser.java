package net.conor.android.cbeeber.util.parser;

import net.conor.android.cbeeber.model.Schedule;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;


public class ScheduleParser {

    public Schedule parse(String xml) {
        Serializer serial = new Persister();
        try {
            return serial.read(Schedule.class, xml);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}