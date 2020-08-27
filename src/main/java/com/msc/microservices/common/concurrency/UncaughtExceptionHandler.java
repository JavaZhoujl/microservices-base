package com.msc.microservices.common.concurrency;

/**
 * 异步异常处理
 *
 * @author zjl
 */
@FunctionalInterface
public interface UncaughtExceptionHandler {
    /**
     * 异常处理
     *
     * @param e 异常
     */
    void uncaughtException(Throwable e);
}
