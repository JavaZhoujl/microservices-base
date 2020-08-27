package com.msc.microservices.common.consts;

/**
 * 常用的字符串常量
 *
 * @author zjl
 */
public final class StringConst {
    /**
     * 网关解析token后传递给服务的用户信息header
     */
    public static final String GATEWAY_USER_HEADER = "X-Gateway-User";
    /**
     * 服务间相互调用传递的用户信息header
     */
    public static final String SERVICE_USER_HEADER = "X-Service-User";
    /**
     * 服务间相互调用传递的用户header(为了向后兼容,避免切换header时造成无法获取获取用户信息,任然传递这个header)
     */
    @Deprecated
    public static final String FEIGN_USER_HEADER = "X-Feign-User";
    /**
     * 服务调用源名称
     */
    public static final String REFER_SERVICE_NAME = "Refer-Service-Name";
    /**
     * 服务调用源主机
     */
    public static final String REFER_REQUEST_HOST = "Refer-Request-Host";
    /**
     * 网关追踪编号
     */
    public static final String GATEWAY_TRACE = "Gateway-Trace";
    /**
     * 内网ip
     */
    public static final String HOST_IP = System.getenv("HOST_IP");
    /**
     * jwt bearer头
     */
    public static final String BEARER = "Bearer ";
    /**
     * authorization头
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * 项目名称key
     */
    public static final String PROJECT_NAME_KEY = "project.name";
    /**
     * 项目名称
     */
    public static final String PROJECT_NAME = System.getProperty(PROJECT_NAME_KEY);
}
