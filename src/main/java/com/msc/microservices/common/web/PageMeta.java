package com.msc.microservices.common.web;

import java.io.Serializable;

/**
 * 带分页的meta对象
 *
 * @author zjl
 */
public final class PageMeta extends EmptyMeta implements Serializable {
    private Page pagination;

    public PageMeta() {

    }

    public PageMeta(Page page) {
        this.pagination = page;
    }

    public Page getPagination() {
        return pagination;
    }

    public void setPagination(Page pagination) {
        this.pagination = pagination;
    }

    @Override
    public String toString() {
        return "PageMeta{" +
                "pagination=" + pagination +
                super.toString() +
                '}';
    }
}
