package com.msc.microservices.common.base;


import com.msc.microservices.common.util.CollectionUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 业务层方法返回的富化结果对象
 *
 * @author zjl
 */
public final class Result<T> implements Serializable {
    /**
     * 业务方法执行是否成功
     */
    private boolean success;
    /**
     * 业务返回实体对象
     */
    private T data;
    /**
     * 错误列表
     */
    private List<Error> errors;
    /**
     * 业务码
     */
    private String code;

    private Result(boolean success, T data, List<Error> errors, String code) {
        this.success = success;
        this.data = data;
        this.errors = errors;
        this.code = code;
    }

    public static <T> Result<T> success(T data) {
        return success(data, null);
    }

    public static <T> Result<T> success(T data, String code) {
        return new Result<>(true, data, null, code);
    }

    public static <T> Result<T> fail(List<Error> errors) {
        return fail(null, errors);
    }

    public static <T> Result<T> fail(List<Error> errors, String code) {
        return fail(null, code, errors);
    }

    public static <T> Result<T> fail(Error... errors) {
        return fail(null, errors);
    }

    public static <T> Result<T> fail(String code, Error... errors) {
        return new Result<>(false, null, CollectionUtil.notNull(errors), code);
    }

    public static <T> Result<T> fail(T data, List<Error> errors) {
        return new Result<>(false, data, CollectionUtil.notNull(errors), null);
    }

    public static <T> Result<T> fail(T data, String code, List<Error> errors) {
        return new Result<>(false, data, CollectionUtil.notNull(errors), code);
    }

    /**
     * 特别注意如果泛型T为String时,此方法永远不会被调用,因为fail(String code,Error... errors)方法第一个参数显示声明为String,优先级更高,此时推荐使用fail(T
     * data,String code,Error... erros)方法
     *
     * @param data   数据
     * @param errors 错误可变参数
     * @param <T>    泛型
     * @return result
     */
    public static <T> Result<T> fail(T data, Error... errors) {
        return new Result<>(false, data, CollectionUtil.notNull(errors), null);
    }

    public static <T> Result<T> fail(T data, String code, Error... errors) {
        return new Result<>(false, data, CollectionUtil.notNull(errors), code);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", data=" + data +
                ", errors=" + errors +
                ", code='" + code + '\'' +
                '}';
    }
}
