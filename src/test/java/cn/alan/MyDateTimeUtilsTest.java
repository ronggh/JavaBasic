package cn.alan;

import java.util.Date;

import cn.alan.utils.MyDateTimeUtils;

public class MyDateTimeUtilsTest {
    public static void main(String[] args) {
        System.out.println("Current Date Time: " + MyDateTimeUtils.currentDateTime());
        System.out.println("Current Date Time String: " + MyDateTimeUtils.currentDateTimeString("YYYY/MM/dd HH:mm:ss"));
        System.out.println("Current Date Time String: " + MyDateTimeUtils.currentDateTimeString());
        System.out.println("Current Date Time Chinese String: " + MyDateTimeUtils.currentDateTimeChineseString());
        System.out.println("Date format: " + MyDateTimeUtils.format(new Date()));
        System.out.println("currentTimeMillis : " + MyDateTimeUtils.currentTimeMillis());
        System.out.println("calcInterval : "
            + MyDateTimeUtils.calcInterval(MyDateTimeUtils.string2Date("2020-11-22 12:34:00"), new Date(), 2));
        System.out.println("date2timestamp :" + MyDateTimeUtils.date2Timestamp(new Date()));
        System.out.println("timestamp2date :" + MyDateTimeUtils.timestamp2Date(MyDateTimeUtils.currentTimeMillis()));
        System.out.println("afterDays :" + MyDateTimeUtils.afterDays(MyDateTimeUtils.currentDate(), -1));
    }
}
