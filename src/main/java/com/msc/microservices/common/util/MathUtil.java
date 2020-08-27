package com.msc.microservices.common.util;

import java.util.Random;

/**
 * 数学相关工具类
 *
 * @author zjl
 */
public final class MathUtil {
    /**
     * 获取正态分布的随机值
     *
     * @param avg      平均值
     * @param variance 方差
     * @return 随机值
     */
    public static double getGaussianRandom(double avg, double variance) {
        return Math.sqrt(variance) * new Random().nextGaussian() + avg;
    }
}
