package com.msc.microservices.common.system;

import java.util.concurrent.CountDownLatch;

/**
 * 优雅关闭动作规范接口
 *
 * @author zjl
 */
public interface Graceful {
    /**
     * 执行优雅关闭动作,此时不再接受任何新的服务,但必须保证已接受的服务完成,执行完毕后计数器+1.管理者会有一个等待超时时间,请保证关闭动作尽快完成
     *
     * @param countDownLatch 计数器
     * @return 执行结果
     */
    Result grace(CountDownLatch countDownLatch);

    /**
     * 优先级
     *
     * @return 优先级, 值越小, 优先级越高
     */
    int getPriority();
}
