package com.msc.microservices.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 业务日志类
 *
 * @author zjl
 */
public final class AppLogger implements SpecificLogger {
    /**
     * 业务日志名称
     */
    private static final String APP_LOGGER_STR = "appLogger";
    /**
     * 业务日志静态实例类
     */
    private static final SpecificLogger APP_LOGGER = new AppLogger();
    /**
     * 业务日志缓存
     */
    private static final ConcurrentHashMap<String, SpecificLogger> CACHE = new ConcurrentHashMap<>();
    /**
     * slf4j Logger接口
     */
    private final Logger logger;

    private AppLogger() {
        logger = LoggerFactory.getLogger(APP_LOGGER_STR);
    }

    private AppLogger(String logName) {
        logger = LoggerFactory.getLogger(logName);
    }

    private AppLogger(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public static SpecificLogger getInstance() {
        return APP_LOGGER;
    }

    public static SpecificLogger getInstance(String logName) {
        return CACHE.computeIfAbsent(logName, s -> new AppLogger(logName));
    }

    public static SpecificLogger getInstance(Class<?> clazz) {
        String className = clazz.getName();
        return CACHE.computeIfAbsent(className, s -> new AppLogger(clazz));
    }

    @Override
    public void log(String msg) {
        logger.info(msg);
    }

    @Override
    public void log(String format, Object arg) {
        logger.info(format, arg);
    }

    @Override
    public void log(String format, Object arg1, Object arg2) {
        logger.info(format, arg1, arg2);
    }

    @Override
    public void log(String format, Object... arguments) {
        logger.info(format, arguments);
    }

    @Override
    public void log(String msg, Throwable t) {
        logger.info(msg, t);
    }

    @Override
    public void log(Marker marker, String msg) {
        logger.info(marker, msg);
    }

    @Override
    public void log(Marker marker, String format, Object arg) {
        logger.info(marker, format, arg);
    }

    @Override
    public void log(Marker marker, String format, Object arg1, Object arg2) {
        logger.info(marker, format, arg1, arg2);
    }

    @Override
    public void log(Marker marker, String format, Object... arguments) {
        logger.info(marker, format, arguments);
    }

    @Override
    public void log(Marker marker, String msg, Throwable t) {
        logger.info(marker, msg, t);
    }
}
