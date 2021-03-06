package com.msc.microservices.common.util.sql;

/**
 * 批量sql迭代器
 *
 * @author zjl
 */
@FunctionalInterface
public interface SqlIterator<T> {
    /**
     * 迭代生成SQL
     *
     * @param param 当前参数
     * @param index 索引值
     * @return SQL
     */
    SQL iterate(T param, int index);
}
