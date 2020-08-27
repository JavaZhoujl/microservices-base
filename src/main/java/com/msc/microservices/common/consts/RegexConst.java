package com.msc.microservices.common.consts;

import java.util.regex.Pattern;

/**
 * 正则表达式常量类
 *
 * @author zjl
 */
public final class RegexConst {
    /**
     * 多个id使用逗号拼接的字符串正则字符串
     */
    public static final String IDS_COMMA_STR_REGEX = "\\d+(,\\d+)*";
    /**
     * 多个id使用逗号拼接的字符串
     */
    public static final Pattern IDS_COMMA_STR = Pattern.compile(IDS_COMMA_STR_REGEX);
    /**
     * 手机正则字符串
     */
    public static final String PHONE_REGEX = "^[1][3-9][0-9]{9}$";
    /**
     * 手机正则
     */
    public static final Pattern PHONE = Pattern.compile(PHONE_REGEX);
    /**
     * 邮箱正则字符串
     */
    public static final String EMAIL_REGEX = "[a-zA-Z0-9_-]+@\\w+\\.[a-z]+(\\.[a-z]+)?";
    /**
     * 邮箱正则
     */
    public static final Pattern EMAIL = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
}
