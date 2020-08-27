package com.msc.microservices.common.exception;

/**
 * 事务状态不合理数据层异常,用于处理事务同步时抛出异常触发回滚
 *
 * @author zjl
 */
public class TransactionIllegalStateDaoException extends IllegalStateException {
    private final String key;

    public TransactionIllegalStateDaoException(String message) {
        this("", message);
    }

    public TransactionIllegalStateDaoException(String key, String message) {
        super(message);
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
