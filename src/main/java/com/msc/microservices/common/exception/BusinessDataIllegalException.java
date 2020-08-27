package com.msc.microservices.common.exception;

/**
 * 业务数据非法异常
 *
 * @author zjl
 */
public class BusinessDataIllegalException extends RuntimeException {
    public BusinessDataIllegalException(String message) {
        super(message);
    }

    public BusinessDataIllegalException(Throwable throwable) {
        super(throwable);
    }

    public BusinessDataIllegalException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
