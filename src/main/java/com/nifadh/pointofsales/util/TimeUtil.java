package com.nifadh.pointofsales.util;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static String getSriLankanDateTimeString() {
        ZoneId sriLankaZone = ZoneId.of("Asia/Colombo");
        ZonedDateTime sriLankanTime = ZonedDateTime.now(sriLankaZone);
        return sriLankanTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
}
