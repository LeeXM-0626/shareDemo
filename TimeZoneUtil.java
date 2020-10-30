package com.zkteco.dbs.common.util;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

@Component
public class TimeZoneUtil {


    /**
     * 获取某月第一天
     *
     * @return
     */
    public static LocalDateTime getFirstLocalDayOfMonth(LocalDateTime localDateTime) {
        return localDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
    }

    /**
     * 获取几月份的第几个星期
     *
     * @param originTime
     * @return
     */
    public static String getMonthNoAndWeekNo(String originTime) {
        StringBuffer sb = new StringBuffer();
        if(StringUtils.isBlank(originTime)){
            return sb.toString();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime sourceTime = LocalDateTime.parse(originTime,dtf);
        DecimalFormat df = new DecimalFormat("00");
        // 获取月份
        int month = sourceTime.getMonthValue();
        // 获取小时
        int hour = sourceTime.getHour();
        //获取当月的第一天
        LocalDateTime firstDayOfMonth = getFirstLocalDayOfMonth(sourceTime);

        // 计算当月第一天和参数日期相差的天数
        Duration duration = Duration.between(firstDayOfMonth,sourceTime);
        long diffDays = duration.toDays();
        // 当天是 当月的第几个星期
        long weekNo = (diffDays / 7) + 1;
        // 当天是星期几
        int dayOfweek = sourceTime.getDayOfWeek().getValue();
        sb.append(df.format(month));
        sb.append(df.format(weekNo == 5 ? 11 : weekNo));
        sb.append(df.format(dayOfweek == 7 ? 0 : dayOfweek));
        sb.append(df.format(hour));
        return sb.toString();
    }

    public static void main(String[] args) {
//        addTimeZone();
//        TimeZone tz = TimeZone.getTimeZone("America/New_York"); //America/Mexico_City
//        ZoneId zoneId = tz.toZoneId();
//        String str = zoneId.getRules().getStandardOffset(Instant.now()).toString();
//
//        System.out.println(StringUtils.equals(StringUtils.lowerCase(str), "z"));
//        System.out.println();

        String curTime = "2020-10-29T11:30:00";


        System.out.println(getMonthNoAndWeekNo(curTime));




    }

//    public static void main(String[] args) {
//        String[] allTimeZoneID = TimeZone.getAvailableIDs();
//        List<Map<String, Object>> list = new ArrayList<>();
//        List<TimeZone> timeZoneList = new ArrayList<TimeZone>();
//        for (String tzID : allTimeZoneID) {
//            TimeZone tz = TimeZone.getTimeZone(tzID);
//            timeZoneList.add(tz);
//        }
//        for (TimeZone timeZone : timeZoneList) {
//            if(timeZone.useDaylightTime()){
//
//            ZoneId zoneId = timeZone.toZoneId();
//            ZoneRules rules = zoneId.getRules();
//            System.out.println(timeZone.getID()+"--"+timeZone.toZoneId()+"--"+ rules.getStandardOffset(Instant.now())+"--"+timeZone.useDaylightTime()+"--"+timeZone.getDisplayName(true,TimeZone.LONG));
//            }
//        }
//    }
}
