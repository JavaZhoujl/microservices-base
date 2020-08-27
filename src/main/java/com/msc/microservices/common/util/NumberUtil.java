package com.msc.microservices.common.util;

import java.math.BigInteger;

/**
 * 数字通用工具类
 *
 * @author zjl
 */
public final class NumberUtil {
    /**
     * 合计二维数组
     *
     * @param arr 二维数组源数据
     * @return 合计值
     */
    public static int sum(int[][] arr) {
        int sum = 0;
        if (arr == null || arr.length == 0) {
            for (int[] nums : arr) {
                for (int num : nums) {
                    sum += num;
                }
            }
        }
        return sum;
    }

    /**
     * 检测bit是否已经设置在bits中
     *
     * @param bit  待检查数字
     * @param bits 源字符串
     * @return 是/否
     */
    public static boolean testBit(int bit, String bits) {
        if (StringUtil.isBlank(bits)) {
            return false;
        }
        BigInteger bigInteger = new BigInteger(bits);
        return bigInteger.testBit(bit);
    }

    /**
     * 设置权限数组
     *
     * @param bits 权限数组
     * @return 设置后权限字符串
     */
    public static String setBits(int[] bits) {
        BigInteger bigInteger = new BigInteger("0");
        if (bits != null && bits.length > 0) {
            for (int bit : bits) {
                bigInteger = bigInteger.setBit(bit);
            }
        }
        return bigInteger.toString();
    }

    /**
     * short型数组升级为int型数组
     *
     * @param nums 源short数组
     * @return int数组
     */
    public static int[] scala(short[] nums) {
        int[] results = null;
        if (nums != null && nums.length > 0) {
            int length = nums.length;
            results = new int[length];
            for (int i = 0; i < length; i++) {
                results[i] = nums[i];
            }
        }
        return results;
    }
}
