package com.msc.microservices.common.concurrency;

import com.msc.microservices.common.logging.ErrorLogger;

/**
 * @Description 默认实现
 * @Author zjl
 * @Date 2019/8/27 下午 15:12
 * @Version 1.0
 **/
public class DefaultUncaughtExceptionHandler implements UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Throwable e) {
        ErrorLogger.getInstance().log("threadPool uncaughtException", e);
    }
}
