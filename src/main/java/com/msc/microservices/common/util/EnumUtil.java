package com.msc.microservices.common.util;

/**
 * 枚举工具类
 *
 * @author zjl
 */
public final class EnumUtil {
    /**
     * 根据枚举类型class获取枚举值数组
     *
     * @param enumClass 枚举类型class
     * @param <T>       枚举类型
     * @return 枚举值数组
     */
    public static <T extends Enum> T[] getEnums(Class<T> enumClass) {
        return enumClass.getEnumConstants();
    }
}
