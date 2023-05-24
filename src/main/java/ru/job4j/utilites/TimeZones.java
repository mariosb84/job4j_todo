package ru.job4j.utilites;

import ru.job4j.model.Task;

import java.time.ZoneId;
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

    public static Task changeTimeZoneTask(Task task) {
        if (("").equals(task.getUser().getTimezone())) {
            task.setCreated(task.getCreated().
                    atZone(ZoneId.of("UTC+3")).
                    withZoneSameInstant(ZoneId.of(String.valueOf(
                            TimeZone.getDefault()))).toLocalDateTime());
        } else {
            task.setCreated(task.getCreated().
                    atZone(ZoneId.of("UTC+3")).
                    withZoneSameInstant(ZoneId.of(task.getUser().
                            getTimezone().split(":")[0].trim())).toLocalDateTime());
        }
        return task;
    }

}

