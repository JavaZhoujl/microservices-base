package com.msc.microservices.common.concurrency;

import java.util.ServiceLoader;

/**
 * 插件工具类
 *
 * @author zjl
 */
public final class Plugins {
    private static MDCConcurrencyStrategy mdcConcurrencyStrategy = null;

    static {
        ServiceLoader<MDCConcurrencyStrategy> serviceLoader = ServiceLoader.load(MDCConcurrencyStrategy.class);
        for (MDCConcurrencyStrategy strategy : serviceLoader) {
            if (strategy != null) {
                mdcConcurrencyStrategy = strategy;
                break;
            }
        }
        if (mdcConcurrencyStrategy == null) {
            mdcConcurrencyStrategy = new MDCConcurrencyStrategy.Default();
        }
    }

    public static MDCConcurrencyStrategy getMDCConcurrencyStrategy() {
        return mdcConcurrencyStrategy;
    }
}
