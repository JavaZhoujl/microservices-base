package com.msc.microservices.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 错误日志类
 *
 * @author zjl
 */
public final class ErrorLogger implements SpecificLogger {
    /**
     * 错误日志名称
     */
    private static final String ERROR_LOGGER_STR = "errorLogger";
    /**
     * 错误日志静态实例类
     */
    private static final SpecificLogger ERROR_LOGGER = new ErrorLogger();
    /**
     * 错误日志缓存
     */
    private static final ConcurrentHashMap<String, SpecificLogger> CACHE = new ConcurrentHashMap<>();
    /**
     * slf4j Logger接口
     */
    private final Logger logger;

    private ErrorLogger() {
        logger = LoggerFactory.getLogger(ERROR_LOGGER_STR);
    }

    private ErrorLogger(String logName) {
        logger = LoggerFactory.getLogger(logName);
    }

    private ErrorLogger(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public static SpecificLogger getInstance() {
        return ERROR_LOGGER;
    }

    public static SpecificLogger getInstance(String logName) {
        return CACHE.computeIfAbsent(logName, s -> new ErrorLogger(logName));
    }

    public static SpecificLogger getInstance(Class<?> clazz) {
        String className = clazz.getName();
        return CACHE.computeIfAbsent(className, s -> new ErrorLogger(clazz));
    }

    @Override
    public void log(String msg) {
        logger.error(msg);
    }

    @Override
    public void log(String format, Object arg) {
        logger.error(format, arg);
    }

    @Override
    public void log(String format, Object arg1, Object arg2) {
        logger.error(format, arg1, arg2);
    }

    @Override
    public void log(String format, Object... arguments) {
        logger.error(format, arguments);
    }

    @Override
    public void log(String msg, Throwable t) {
        logger.error(msg, t);
    }

    @Override
    public void log(Marker marker, String msg) {
        logger.error(marker, msg);
    }

    @Override
    public void log(Marker marker, String format, Object arg) {
        logger.error(marker, format, arg);
    }

    @Override
    public void log(Marker marker, String format, Object arg1, Object arg2) {
        logger.error(marker, format, arg1, arg2);
    }

    @Override
    public void log(Marker marker, String format, Object... arguments) {
        logger.error(marker, format, arguments);
    }

    @Override
    public void log(Marker marker, String msg, Throwable t) {
        logger.error(marker, msg, t);
    }
}
