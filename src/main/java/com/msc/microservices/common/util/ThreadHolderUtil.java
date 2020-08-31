package com.msc.microservices.common.util;

import com.msc.microservices.common.base.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 线程变量工具类
 *
 * @author zjl
 */
public final class ThreadHolderUtil {
    /**
     * 当前请求线程用户信息
     */
    private static final ThreadLocal<User> USER_HOLDER = new ThreadLocal<>();

    /**
     * 当前请求线程原始payload字符串
     */
    @Deprecated
    public static final String PAYLOAD_KEY = "payload";
    /**
     * 服务追踪信息名称
     */
    public static final String TRACE_KEY = "trace";
    /**
     * 网关消费者id
     */
    @Deprecated
    public static final String CONSUMER_ID_KEY = "consumerId";
    /**
     * 网关消费者客户id
     */
    @Deprecated
    public static final String CONSUMER_CUSTOM_ID_KEY = "consumerCustomId";
    /**
     * 网关消费者名称
     */
    @Deprecated
    public static final String CONSUMER_USERNAME_KEY = "consumerUsername";

    /**
     * 任意类型数据集合
     */
    private static final ThreadLocal<Map<Object, Object>> VALUE_MAP = ThreadLocal.withInitial(HashMap::new);

    /**
     * 设置当前请求线程用户对象
     *
     * @param user 用户对象
     */
    public static void setUser(User user) {
        USER_HOLDER.set(user);
    }

    /**
     * 获取当前请求线程用户编号
     *
     * @return 用户编号
     */
    public static int getUserId() {
        User user = USER_HOLDER.get();
        return Optional.ofNullable(user).map(User::getUserId).orElse(0);
    }

    /**
     * 获取当前请求线程账户编号
     *
     * @return 账户编号
     */
    public static long getAcId() {
        User user = USER_HOLDER.get();
        return Optional.ofNullable(user).map(User::getAcId).orElse(0L);
    }

    /**
     * 获取当前请求线程用户编号
     *
     * @return 用户姓名
     */
    public static String getUserName() {
        User user = USER_HOLDER.get();
        return Optional.ofNullable(user).map(User::getUserName).orElse(null);
    }

    /**
     * 获取当前请求线程用户
     *
     * @return 用户
     */
    public static User getUser() {
        return USER_HOLDER.get();
    }

    /**
     * 获取当前用户所属组织数组
     *
     * @return 所属组织数组
     */
    public static int getOrgId() {
        User user = USER_HOLDER.get();
        return Optional.ofNullable(user).map(User::getOrgId).orElse(0);
    }

    /**
     * 获取当前请求线程供应商编号
     *
     * @return 供应商编号
     */
    public static int getSupId() {
        User user = USER_HOLDER.get();
        return Optional.ofNullable(user).map(User::getSupId).orElse(0);
    }

    /**
     * 获取当前请求线程供应商名称
     *
     * @return 供应商名称
     */
    public static String getSupName() {
        User user = USER_HOLDER.get();
        return Optional.ofNullable(user).map(User::getSupName).orElse(null);
    }

    /**
     * 检验当前用户是否为供应商
     *
     * @return 是否为供应商
     */
    public static boolean isSup() {
        return getSupId() > 0;
    }

    /**
     * 检验当前用户是否为采购员 // TODO 逻辑待完善
     *
     * @return 是否为采购员
     */
    public static boolean isPurchase() {
        return getUserId() > 0 && !isSup();
    }

    public static void clearUser() {
        USER_HOLDER.remove();
    }

    /**
     * 根据key获取值
     *
     * @param key   key
     * @param clazz 值类型
     * @return 值
     */
    public static <T> T getValue(Object key, Class<T> clazz) {
        Object value = Optional.ofNullable(VALUE_MAP.get()).map(map -> map.get(key)).orElse(null);
        try {
            return (T) value;
        } catch (Throwable ex) {
            return null;
        }
    }

    /**
     * 获取map
     */
    public static Map<Object, Object> getValueMap() {
        return VALUE_MAP.get();
    }

    /**
     * 设置key值
     *
     * @param key   key
     * @param value 值
     */
    public static void setValue(Object key, Object value) {
        Optional.ofNullable(VALUE_MAP.get()).ifPresent(valueMap -> valueMap.put(key, value));
    }

    /**
     * 覆盖map
     *
     * @param values map值
     */
    public static void setValueMap(Map<Object, Object> values) {
        VALUE_MAP.set(values);
    }

    /**
     * 清除指定Key
     *
     * @param key 指定key
     */
    public static void clearValue(Object key) {
        Optional.ofNullable(VALUE_MAP.get()).ifPresent(valueMap -> valueMap.remove(key));
    }

    /**
     * 清除整个map
     */
    public static void clearValueMap() {
        VALUE_MAP.remove();
    }
}
