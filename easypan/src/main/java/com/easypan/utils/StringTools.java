package com.easypan.utils;

import org.apache.commons.lang.RandomStringUtils;

public class StringTools {
    /**
     * 生成随机数
     * 自定义的字符串工具
     * @param count
     * @return
     */
    public static final String getRandomNumber(Integer count) {
        return RandomStringUtils.random(count, false, true);
    }

    public static boolean isEmpty(String toString) {
        if(toString.length() == 0) return true;
        return false;
    }
}
