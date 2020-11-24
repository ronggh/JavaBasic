package cn.alan.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MyDateTimeUtils {
    public static final String kDefaultFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String kDefaultDateFormat = "yyyy-MM-dd";
    public static final String kDefaultTimeFormat = "HH:mm:ss";

    /**
     * 获取当前日期和时间
     * 
     * @return
     */
    public static Date currentDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 获取当前日期，不含时间
     * 
     * @return
     */
    public static Date currentDate() {
        Date date = currentDateTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getDefault());
        try {
            return sdf.parse(sdf.format(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 当前时间的毫秒数
     * 
     * @return
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 返回当前日期和时间
     * 
     * @param format
     *            指定格式
     * 
     * @return
     */
    public static String currentDateTimeString(String format) {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(now.getTime()));
    }

    /**
     * 返回当前时间，格式为 yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String currentDateTimeString() {
        return (currentDateTimeString(kDefaultFormat));
    }

    /**
     * 回当前时间，格式为 yyyy年MM月dd日 HH:mm:ss
     * 
     * @return
     */
    public static String currentDateTimeChineseString() {
        return (currentDateTimeString("yyyy年MM月dd日 HH:mm:ss"));
    }

    /**
     * 今日
     * 
     * @return
     */
    public static Date today() {
        return currentDate();
    }

    /**
     * 明天
     * 
     * @return
     */
    public static Date tomorrow() {
        return afterDays(today(), 1);
    }

    /**
     * 昨天
     * 
     * @return
     */
    public static Date yesterday() {
        return afterDays(today(), -1);
    }

    /**
     * 格式化日期时间为指定的格式
     * 
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 格式化日期时间格式为： yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, kDefaultFormat);
    }

    /**
     * 仅取日期
     * 
     * @param date
     * @return
     */
    public static String formatShortDate(Date date) {
        return format(date, kDefaultDateFormat);
    }

    /**
     * 仅取时间
     * 
     * @param date
     * @return
     */
    public static String formatShortTime(Date date) {
        return format(date, kDefaultTimeFormat);
    }

    /**
     * 日期转字符串
     */
    public static String date2String(Date date, String formatStr) {
        DateFormat df = new SimpleDateFormat(formatStr);
        return df.format(date);
    }

    /**
     * 
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        DateFormat df = new SimpleDateFormat(kDefaultFormat);
        return df.format(date);
    }

    /**
     * 字符串转换到时间格式，自定义日期格式
     */
    public static Date string2Date(String dateStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date string2Date(String dateStr) {
        return string2Date(dateStr, kDefaultFormat);
    }

    /**
     * 日期类型转时间戳
     * 
     * @param date
     * @return
     */
    public static long date2Timestamp(Date date) {
        return date.getTime();
    }

    /**
     * 时间戳转字符串
     * 
     * @param timestamp
     * @return
     */
    public static String timestamp2String(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(kDefaultFormat);
        return sdf.format(timestamp);
    }

    /**
     * 时间戳转日期
     * 
     * @param timestamp
     * @return
     */
    public static Date timestamp2Date(long timestamp) {
        String strDate = timestamp2String(timestamp);
        return string2Date(strDate);
    }

    /**
     * 计算两个日期间的时间差
     * 
     * @param beginDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @param type
     *            返回时间差的类型，0-秒,1-分种,2-小时,3--天
     * @return
     */
    public static int calcInterval(Date beginDate, Date endDate, int type) {
        long millisecond = endDate.getTime() - beginDate.getTime();
        int result = 0;
        switch (type) {
            case 0:
                result = (int)(millisecond / 1000);
                break;
            case 1:
                result = (int)(millisecond / (1000 * 60));
                break;
            case 2:
                result = (int)(millisecond / (1000 * 60 * 60));
                break;
            case 3:
                result = (int)(millisecond / (1000 * 60 * 60 * 24));
                break;
        }
        return result;
    }

    /**
     * 获取指定日期之后的天数的日期
     * 
     * @param beginDate
     *            指定的开始日期
     * @param days
     *            正数，表示其后的日期，负数表示这之前的日期
     * @return
     */
    public static Date afterDays(Date beginDate, int days) {
        Calendar startTime = Calendar.getInstance();
        startTime.clear();
        startTime.setTime(beginDate);

        startTime.add(Calendar.DAY_OF_YEAR, days);
        return startTime.getTime();
    }
}
