package com.msc.microservices.common.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.msc.microservices.common.base.Result;
import com.msc.microservices.common.util.CollectionUtil;
import com.msc.microservices.common.util.ThreadHolderUtil;


import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.List;

import static com.msc.microservices.common.util.ThreadHolderUtil.TRACE_KEY;


/**
 * 通用的http响应内容接口
 *
 * @author zjl
 */
public final class ResponseBody<T, M extends EmptyMeta> implements Serializable {
    /**
     * 成功
     */
    public static final String SUCCESS_MESSAGE = "成功";
    /**
     * 失败
     */
    public static final String FAIL_MSG = "失败";
    /**
     * 成功业务状态码
     */
    public static final String SUCCESS_STATUS_CODE = "0";
    /**
     * 失败业务状态码
     */
    public static String FAIL_STATUS_CODE = "1000";
    /**
     * 服务降级状态码
     */
    private static final String FALLBACK = "fallback";
    /**
     * 业务状态码
     */
    @JsonProperty("status_code")
    private String statusCode;
    /**
     * 简短信息
     */
    private String message;
    /**
     * 内容主体
     */
    private T data;
    /**
     * 额外元数据
     */
    private M meta;
    /**
     * 错误信息
     */
    private List<Error> errors;

    public ResponseBody() {

    }

    private ResponseBody(String statusCode, String message, T data, M meta, List<Error> errors) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.meta = meta;
        this.errors = errors;
    }

    /**
     * 获取成功响应主体
     *
     * @param data 主体对象
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> success(T data) {
        return success(SUCCESS_MESSAGE, data, null);
    }

    /**
     * 获取成功响应主体
     *
     * @param data 主体对象
     * @param meta 元数据
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> success(T data, M meta) {
        return success(SUCCESS_MESSAGE, data, meta);
    }

    /**
     * 获取成功响应主体
     *
     * @param message 成功消息
     * @param data    主体对象
     * @param meta    元数据
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> success(String message, T data, M meta) {
        return new ResponseBody<>(SUCCESS_STATUS_CODE, message, data, processMeta(meta), null);
    }

    /**
     * 获取失败响应主体
     *
     * @param errors 错误列表
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(List<Error> errors) {
        errors = CollectionUtil.notNull(errors);
        return fail(getSimpleFailStatusCode(), getFailMsg(errors), null, null, errors);
    }

    /**
     * 获取失败响应主体
     *
     * @param errors 错误列表
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(Error... errors) {
        return fail(CollectionUtil.notNull(errors));
    }

    /**
     * 获取失败响应主体
     *
     * @param statusCode 业务状态码
     * @param errors     错误列表
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(String statusCode, List<Error> errors) {
        errors = CollectionUtil.notNull(errors);
        return fail(statusCode, getFailMsg(errors), null, null, errors);
    }

    /**
     * 获取失败响应主体
     *
     * @param statusCode 业务状态码
     * @param errors     错误
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(String statusCode, Error... errors) {
        return fail(statusCode, CollectionUtil.notNull(errors));
    }

    /**
     * 获取失败响应主体
     *
     * @param statusCode 业务状态码
     * @param message    失败消息
     * @param errors     错误列表
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(String statusCode, String message, List<Error> errors) {
        return fail(statusCode, message, null, null, errors);
    }

    /**
     * 获取失败响应主体
     *
     * @param statusCode 业务状态码
     * @param message    失败消息
     * @param errors     错误列表
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(String statusCode, String message, Error... errors) {
        return fail(statusCode, message, null, null, errors);
    }

    /**
     * 获取失败响应主体
     *
     * @param data   主体对象
     * @param errors 错误列表
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(T data, List<Error> errors) {
        return fail(getSimpleFailStatusCode(), getFailMsg(errors), data, null, errors);
    }

    /**
     * 获取失败响应主体
     *
     * @param data   主体对象
     * @param errors 错误列表
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(T data, Error... errors) {
        return fail(data, CollectionUtil.notNull(errors));
    }

    /**
     * 获取失败响应主体
     *
     * @param data   主体对象
     * @param meta   元数据
     * @param errors 错误列表
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(T data, M meta, List<Error> errors) {
        return fail(getSimpleFailStatusCode(), getFailMsg(errors), data, meta, errors);
    }

    /**
     * 获取失败响应主体
     *
     * @param data   主体对象
     * @param meta   元数据
     * @param errors 错误列表
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(T data, M meta, Error... errors) {
        return fail(data, meta, CollectionUtil.notNull(errors));
    }

    /**
     * 获取失败响应主体
     *
     * @param statusCode 业务状态码
     * @param message    失败消息
     * @param data       主体对象
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(String statusCode, String message, T data, M meta, List<Error> errors) {
        return new ResponseBody<>(statusCode, message, data, processMeta(meta), CollectionUtil.notNull(errors));
    }

    /**
     * 获取失败响应主体
     *
     * @param statusCode 业务状态码
     * @param message    失败消息
     * @param data       主体对象
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(String statusCode, String message, T data, M meta, Error... errors) {
        return new ResponseBody<>(statusCode, message, data, processMeta(meta), CollectionUtil.notNull(errors));
    }

    /**
     * 根据result结果自动生成响应主体
     *
     * @param result 结果
     * @return 响应主体
     */
//    public static <T, M extends EmptyMeta> ResponseBody<T, M> result(Result<T> result) {
//        if (!result.isSuccess()) {
//            List<Error> errors = CollectionUtil.notNull(result.getErrors());
//            return fail(getSimpleFailStatusCode(), getFailMsg(errors), result.getData(), null, errors);
//        }
//        return success(result.getData());
//    }

    /**
     * 根据result结果自动生成响应主体
     *
     * @param result 结果
     * @param meta   Meta信息
     * @return 响应主体
     */
//    public static <T, M extends EmptyMeta> ResponseBody<T, M> result(Result<T> result, M meta) {
//        if (!result.isSuccess()) {
//            List<Error> errors = CollectionUtil.notNull(result.getErrors());
//            return fail(getSimpleFailStatusCode(), getFailMsg(errors), result.getData(), meta, errors);
//        }
//        return success(result.getData(), meta);
//    }

    /**
     * 获取服务降级响应体
     *
     * @param errors 错误
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fallback(Error... errors) {
        return fallback(null, errors);
    }

    /**
     * 获取服务降级响应体
     *
     * @param cause  异常
     * @param errors 错误信息
     * @return 响应主体
     */
    public static <T, M extends EmptyMeta> ResponseBody<T, M> fallback(Throwable cause, Error... errors) {
        return fail(FALLBACK, cause == null ? FALLBACK : cause.getMessage(), errors);
    }

    private static String getSimpleFailStatusCode() {
        //return getProperty(SIMPLE_FAIL_STATUS_CODE_PROP, FAIL_STATUS_CODE);
        return FAIL_STATUS_CODE;
    }

    private static String getFailMsg(List<Error> errors) {
        return CollectionUtils.isEmpty(errors) ? FAIL_MSG : errors.stream()
                .map(Error::getError)
                .reduce((err1, err2) -> err1 + "\n" + err2)
                .orElse(FAIL_MSG);
    }

    private static <M extends EmptyMeta> M processMeta(M meta) {
        Trace trace = ThreadHolderUtil.getValue(TRACE_KEY, Trace.class);
        if (meta != null) {
            meta.setTrace(trace);
        } else {
            meta = (M) new EmptyMeta(trace);
        }
        return meta;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public M getMeta() {
        return meta;
    }

    public void setMeta(M meta) {
        this.meta = meta;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    /**
     * 判断当前响应体是否业务成功
     */
    @JsonIgnore
    public boolean isSuccess() {
        return SUCCESS_STATUS_CODE.equals(statusCode);
    }

    /**
     * 判断当前响应体是否服务降级
     */
    @JsonIgnore
    public boolean isFallback() {
        return FALLBACK.equals(statusCode);
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", meta=" + meta +
                ", errors=" + errors +
                '}';
    }
}
