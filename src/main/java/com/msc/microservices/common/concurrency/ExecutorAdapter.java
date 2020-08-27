package com.msc.microservices.common.concurrency;

import java.util.concurrent.Executor;

/**
 * @Description {@link Executor}适配器,包装{@link Runnable}
 * @Author zjl
 * @Date 2019/8/27 下午 15:18
 * @Version 1.0
 **/
public class ExecutorAdapter implements Executor {
    private final Executor executor;

    public ExecutorAdapter(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Runnable command) {
        executor.execute(new RequestContextRunnable(command));
    }
}