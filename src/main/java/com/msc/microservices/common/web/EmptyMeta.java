package com.msc.microservices.common.web;

import java.io.Serializable;

/**
 * 标志一个空的meta对象
 *
 * @author zjl
 */
public class EmptyMeta implements Serializable {
    private Trace trace;

    public EmptyMeta() {

    }

    public EmptyMeta(Trace trace) {
        this.trace = trace;
    }

    public Trace getTrace() {
        return trace;
    }

    public void setTrace(Trace trace) {
        this.trace = trace;
    }

    @Override
    public String toString() {
        return "EmptyMeta{" +
                "trace=" + trace +
                '}';
    }
}
