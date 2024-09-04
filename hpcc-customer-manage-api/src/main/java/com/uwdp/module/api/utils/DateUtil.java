package com.uwdp.module.api.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 　　　　 ┏┓       ┏┓+ +
 * 　　　　┏┛┻━━━━━━━┛┻┓ + +
 * 　　　　┃　　　　　　 ┃
 * 　　　　┃　　　━　　　┃ ++ + + +
 * 　　　 █████━█████  ┃+
 * 　　　　┃　　　　　　 ┃ +
 * 　　　　┃　　　┻　　　┃
 * 　　　　┃　　　　　　 ┃ + +
 * 　　　　┗━━┓　　　 ┏━┛
 * 　　　　　　┃　　  ┃
 * 　　　　　　┃　　  ┃ + + + +
 * 　　　　　　┃　　　┃　Code is far away from bug with the animal protection
 * 　　　　　　┃　　　┃ + 　　　　         神兽保佑,代码永无bug
 * 　　　　　　┃　　　┃
 * 　　　　　　┃　　　┃　　+
 * 　　　　　　┃　 　 ┗━━━┓ + +
 * 　　　　　　┃ 　　　　　┣┓
 * 　　　　　　┃ 　　　　　┏┛
 * 　　　　　　┗┓┓┏━━━┳┓┏┛ + + + +
 * 　　　　　　 ┃┫┫　 ┃┫┫
 * 　　　　　　 ┗┻┛　 ┗┻┛+ + + +
 * *********************************************************
 * 时间工具类
 *
 * @ClassName: DateUtil
 * @Author: 曾令亮
 * @Date: 2023-09-07 上午 11:35
 */
public class DateUtil {
    private static final String datePattern = "yyyy-MM-dd";
    private static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
    /**
     *  时间转时间字符串
     * @param date  日期
     * @param pattern  格式
     * @return String
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, pattern);
    }
    /**
     *  时间转时间字符串为yyyy-MM-dd格式
     * @param date  日期
     * @return String
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, datePattern);
    }
    /**
     *  时间转时间字符串为yyyy-MM-dd格式
     * @param date  日期
     * @return String
     */
    public static String formatDateYMDHMS(Date date) {
        if (date == null) {
            return "";
        }
        return DateFormatUtils.format(date, dateTimePattern);
    }
    /**
     *  时间字符串转化为yyyy-MM-dd格式
     * @param str  日期
     * @return Date
     */
    public static Date parseDate(String str) {
        if (str == null) {
            return null;
        }
        try {
            return DateUtils.parseDate(str, datePattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *  字符串转时间
     * @param str  字符串
     * @param dateTimePattern  格式
     * @return Date
     */
    public static Date parseDateTime(String str, String dateTimePattern) {
        if (str == null) {
            return null;
        }
        try {
            return DateUtils.parseDate(str, Locale.CHINESE, dateTimePattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取当年的第一天
     */
    public static Date getCurrentFirstOfYear(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getFirstOfYear(currentYear);
    }
    /**
     * 获取当年的最后一天
     */
    public static Date getCurrentLastOfYear(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getLastOfYear(currentYear);
    }
    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getFirstOfYear(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }
    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getLastOfYear(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 获取今天开始时间
     * @return
     */
    public static Date getStartTime(){
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();

        // 设置时分秒为0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取今天结束时间
     * @return
     */
    public static Date getEndTime(){
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();

        // 设置时分秒为0
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }
}
