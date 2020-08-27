package com.msc.microservices.common.concurrency;

/**
 * MDC应用策略
 *
 * @author zjl
 */
public interface MDCConcurrencyStrategy {
    /**
     * 设置
     */
    void put();

    /**
     * 清除
     */
    void clear();

    /**
     * 默认实现类
     *
     * @author ty
     */
    class Default implements MDCConcurrencyStrategy {

        @Override
        public void put() {

        }

        @Override
        public void clear() {

        }
    }
}
