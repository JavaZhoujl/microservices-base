package com.msc.microservices.common.util.sql;

import java.util.Map;

/**
 * value迭代器
 *
 * @author zjl
 */
@FunctionalInterface
public interface ValuesIterator<T> {
    /**
     * 值迭代
     *
     * @param param 参数
     * @param index 索引值
     * @return 值隐射
     */
    Map<String, String> values(T param, int index);
}
