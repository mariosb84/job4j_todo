package ru.job4j.utilites;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class TimeZones {

    public static List<String> getTimeZones() {
        var zones = new ArrayList<TimeZone>();
        var list = new ArrayList<String>();
        for (String timeId : TimeZone.getAvailableIDs()) {
            zones.add(TimeZone.getTimeZone(timeId));
        }
        for (TimeZone zone : zones) {
            list.add(zone.getID() + " : " + zone.getDisplayName());
        }
        return list;
    }
}
