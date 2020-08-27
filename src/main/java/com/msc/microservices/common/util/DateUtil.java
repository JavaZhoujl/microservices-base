package com.msc.microservices.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author zjl
 */
public final class DateUtil {
    /**
     * 默认时间
     */
    public static final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.of(1970, 1, 1, 8, 0, 0);
    /**
     * 开发中常用的日期格式化 yyyy-MM-dd
     */
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 开发中常用的日期格式化 yyyy-MM-dd HH:mm:ss
     */
    public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 开发中常用的日期格式化 yyyy-MM-dd HH:mm:ss SSS
     */
    public static final DateFormat DATE_TIME_MILL_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    /**
     * 获取当前时间秒数时间戳
     *
     * @return 秒数时间戳
     */
    public static long getCurrentTimeSeconds() {
        return (System.currentTimeMillis() / 1000);
    }

    /**
     * 获取当前当前年份的1月1日0点0分0秒的时间戳
     *
     * @return 返回当前年份的1月1日0点0分0秒的时间戳
     */
    public static long getFirstTimeInSecondsOfThisYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取昨日零点时间戳
     */
    public static long getYesterdayBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取昨天结束时间戳
     */
    public static long getYesterdayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取当天零点时间戳
     */
    public static long getTodayBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取前零点时间戳
     */
    public static long getBeforeYesterdayBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -2);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 已天为维度变化时间戳
     *
     * @param original 原始时间戳
     * @param amount   变化值,可以为正负
     * @return 变化后的时间戳
     */
    public static long addDays(long original, int amount) {
        return original + 3600 * 24 * amount;
    }

    /**
     * 已小时为维度变化时间戳
     *
     * @param original 原始时间戳
     * @param amount   变化值,可以为正负
     * @return 变化后的时间戳
     */
    public static long addHours(long original, int amount) {
        return original + 3600 * amount;
    }

    /**
     * 已分钟为维度变化时间戳
     *
     * @param original 原始时间戳
     * @param amount   变化值,可以为正负
     * @return 变化后的时间戳
     */
    public static long addMinutes(long original, int amount) {
        return original + 60 * amount;
    }

    /**
     * 已秒为维度变化时间戳
     *
     * @param original 原始时间戳
     * @param amount   变化值,可以为正负
     * @return 变化后的时间戳
     */
    public static long addSeconds(long original, int amount) {
        return original + amount;
    }

    /**
     * 以月为维度变化时间戳
     *
     * @param original 原始时间戳
     * @param amount   变化值,可以为正负
     * @return 变化后的时间戳
     */
    public static long addMonths(long original, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(original));
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取上个月第一天开始时间
     *
     * @return 上个月第一天开始时间
     */
    public static long getLastMonthBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取本月第一天开始时间
     *
     * @return 本月第一天开始时间
     */
    public static long getThisMonthBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 校验时间是否是系统默认合法时间(>1970-01-01 08:00:00)
     *
     * @param dateTime 时间
     * @return 是否合法
     */
    public static boolean isLegalDateTime(LocalDateTime dateTime) {
        return dateTime != null && dateTime.isAfter(DEFAULT_DATE_TIME);
    }
}
