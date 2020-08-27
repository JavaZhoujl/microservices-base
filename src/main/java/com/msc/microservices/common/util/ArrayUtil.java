package com.msc.microservices.common.util;

/**
 * 数组工具类
 *
 * @author zjl
 */
public final class ArrayUtil {
    /**
     * 字符串数组转int数组
     *
     * @param strArr 字符串数组
     * @return int数组
     */
    public static int[] strToInt(String[] strArr) {
        if (strArr == null) {
            return new int[0];
        }
        int length = strArr.length;
        int[] intArr = new int[length];
        for (int i = 0; i < length; i++) {
            intArr[i] = Integer.valueOf(strArr[i]);
        }
        return intArr;
    }

    /**
     * 字符串数组转short数组
     *
     * @param strArr 字符串数组
     * @return short数组
     */
    public static short[] strToShort(String[] strArr) {
        if (strArr == null) {
            return new short[0];
        }
        int length = strArr.length;
        short[] shortArr = new short[length];
        for (int i = 0; i < length; i++) {
            shortArr[i] = Short.valueOf(strArr[i]);
        }
        return shortArr;
    }

    /**
     * 字符串数组转float数组
     *
     * @param strArr 字符串数组
     * @return float数组
     */
    public static float[] strToFloat(String[] strArr) {
        if (strArr == null) {
            return new float[0];
        }
        int length = strArr.length;
        float[] floatArr = new float[length];
        for (int i = 0; i < length; i++) {
            floatArr[i] = Float.valueOf(strArr[i]);
        }
        return floatArr;
    }

    /**
     * 字符串数组转long数组
     *
     * @param strArr 字符串数组
     * @return long数组
     */
    public static long[] strToLong(String[] strArr) {
        if (strArr == null) {
            return new long[0];
        }
        int length = strArr.length;
        long[] longArr = new long[length];
        for (int i = 0; i < length; i++) {
            longArr[i] = Long.valueOf(strArr[i]);
        }
        return longArr;
    }

    /**
     * 字符串数组转double数组
     *
     * @param strArr 字符串数组
     * @return double数组
     */
    public static double[] strToDouble(String[] strArr) {
        if (strArr == null) {
            return new double[0];
        }
        int length = strArr.length;
        double[] doubleArr = new double[length];
        for (int i = 0; i < length; i++) {
            doubleArr[i] = Integer.valueOf(strArr[i]);
        }
        return doubleArr;
    }

    /**
     * 数组是否包含指定对象
     *
     * @param array        数组
     * @param objectToFind 指定对象
     */
    public static boolean contains(final Object[] array, final Object objectToFind) {
        if (array == null) {
            return false;
        }
        if (objectToFind == null) {
            for (Object anArray : array) {
                if (anArray == null) {
                    return true;
                }
            }
        } else {
            for (Object anArray : array) {
                if (objectToFind.equals(anArray)) {
                    return true;
                }
            }
        }
        return false;
    }
}
