package com.msc.microservices.common.web;

import java.io.Serializable;

/**
 * HTTP接口返回分页数据中的链接
 *
 * @author zjl
 */
public final class Link implements Serializable {
    /**
     * 上一页
     */
    private String previous;
    /**
     * 下一页
     */
    private String next;

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Link{" +
                "previous='" + previous + '\'' +
                ", next='" + next + '\'' +
                '}';
    }
}
