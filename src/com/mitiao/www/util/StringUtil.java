package com.mitiao.www.util;

import java.net.URLEncoder;

/**
 * 提供字符串相关处理的一些方法。
 */
public class StringUtil {
    /**
     * 字符串编码函数。
     * 
     * @param str
     * @param srcCode
     * @param targetCode
     * @return
     */
    public static String encodeStr(String str, String targetCode) {
        try {
            if (str == null) {
                return null;
            }
            byte[] bytesStr = str.getBytes();
            return new String(bytesStr, targetCode);
        } catch (Exception ex) {
            return str;
        }
    }

    /**
     * 字符串编码函数。
     * 
     * @param str
     * @param srcCode
     * @param targetCode
     * @return
     */
    public static String encodeStr(String str, String srcCode, String targetCode) {
        try {
            if (str == null) {
                return null;
            }

            byte[] bytesStr = str.getBytes(srcCode);
            return new String(bytesStr, targetCode);
        } catch (Exception ex) {
            return str;
        }
    }

    /**
     * 判断字符串是否为空字符。
     * 
     * @param value
     * @return
     */
    public static boolean isBlank(String value) {
        boolean ret = false;
        if (value != null && value.equals("")) {
            ret = true;
        }
        return ret;
    }

    /**
     * 判断字符串是否为null。
     * 
     * @param value
     * @return
     */
    public static boolean isNull(String value) {
        return value == null ? true : false;
    }

    /**
     * 判断字符串是否为空字符串或者null。
     * 
     * @param value
     * @return
     */
    public static boolean isNullOrBlank(String value) {
        return isNull(value) || isBlank(value);
    }

    /**
     * 截取字符串前面部分字符,后面加省略号.
     * 
     * @param str
     * @param length
     * @return
     */
    public static String trimWords(String str, int length) {
        String wordStr = str;

        if (wordStr == null) {
            return "";
        }
        if (wordStr.length() <= length) {
            return wordStr;
        }

        wordStr = wordStr.substring(0, length);
        wordStr += "...";
        return wordStr;
    }

    /**
     * 编码带有中文名称Url。
     * 
     * @param url url中的中文
     * @return
     */
    public static String encodeUrl(String url) {
        return encodeUrl(url, "gbk");
    }

    /**
     * 编码带有中文名称Url。
     * 
     * @param url url中的中文
     * @param targetCode 目标字符
     * @return
     */
    public static String encodeUrl(String url, String targetCode) {
        String encodeUrl = "";
        if (StringUtil.isNullOrBlank(url)) {
            return "";
        }
        // 编码并转换空格
        try {
            encodeUrl = URLEncoder.encode(url, targetCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeUrl;
    }
}
