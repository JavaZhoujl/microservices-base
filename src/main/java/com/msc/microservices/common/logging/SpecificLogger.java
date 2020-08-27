package com.msc.microservices.common.logging;

import org.slf4j.Marker;

/**
 * 自定义的logger接口
 *
 * @author zjl
 */
public interface SpecificLogger {
    /**
     * Log a message at the specific level.
     *
     * @param msg the message string to be logged
     */
    void log(String msg);

    /**
     * Log a message at the specific level according to the specified format and argument.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled for the specific
     * level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    void log(String format, Object arg);

    /**
     * Log a message at the specific level according to the specified format and arguments.
     * <p/>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled for the specific
     * level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    void log(String format, Object arg1, Object arg2);

    /**
     * Log a message at the specific level according to the specified format and arguments.
     * <p/>
     * <p>
     * This form avoids superfluous string concatenation when the logger is disabled for the
     * specific level. However, this variant incurs the hidden (and relatively small) cost of
     * creating an <code>Object[]</code> before invoking the method, even if this logger is disabled
     * for specific. The variants taking {@link #log(String, Object) one} and
     * {@link #log(String, Object, Object) two} arguments exist solely in order to avoid this hidden
     * cost.
     * </p>
     *
     * @param format    the format string
     * @param arguments a list of 3 or more arguments
     */
    void log(String format, Object... arguments);

    /**
     * Log an exception (throwable) at the specific level with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    void log(String msg, Throwable t);

    /**
     * Log a message with the specific Marker at the specific level.
     *
     * @param marker The marker specific to this log statement
     * @param msg    the message string to be logged
     */
    void log(Marker marker, String msg);

    /**
     * This method is similar to {@link #log(String, Object)} method except that the marker data is
     * also taken into consideration.
     *
     * @param marker the marker data specific to this log statement
     * @param format the format string
     * @param arg    the argument
     */
    void log(Marker marker, String format, Object arg);

    /**
     * This method is similar to {@link #log(String, Object, Object)} method except that the marker
     * data is also taken into consideration.
     *
     * @param marker the marker data specific to this log statement
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    void log(Marker marker, String format, Object arg1, Object arg2);

    /**
     * This method is similar to {@link #log(String, Object...)} method except that the marker data
     * is also taken into consideration.
     *
     * @param marker    the marker data specific to this log statement
     * @param format    the format string
     * @param arguments a list of 3 or more arguments
     */
    void log(Marker marker, String format, Object... arguments);

    /**
     * This method is similar to {@link #log(String, Throwable)} method except that the marker data
     * is also taken into consideration.
     *
     * @param marker the marker data specific to this log statement
     * @param msg    the message accompanying the exception
     * @param t      the exception (throwable) to log
     */
    void log(Marker marker, String msg, Throwable t);
}
