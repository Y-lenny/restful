package com.rest.restapi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Desc 字符串工具
 * @Author feelingxu@tcl.com:
 * @Date 创建时间：2016年7月1日 下午12:19:24
 * @Version V1.0.0
 */
public class StringUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    /**
     * 将字符串转换为字符串数组,协议按照 : 分割
     *
     * @param str
     * @return
     */
    public static String[] splitStr(String str) {
        String[] arr = null;
        try {
            if (str.contains(":")) {
                arr = str.split(":");
            } else {
                // 只有一条数据，先去掉空格
                arr = new String[1];
                arr[0] = str.replace(" ", "").trim();
            }
        } catch (Exception e) {
            LOGGER.error("splitStr  Exception str is {}", str);
        }
        return arr;
    }


    /**
     * 判断字符串是否为空，如果为空，返回true。否则返回false。
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * 判断字符串是否非空，如果不是空，返回true。如果是空，返回false。
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    /**
     * 将list转换为redis key
     *
     * @param parametersList
     * @return String
     */
    public static String listToString(List<Map<String, String>> parametersList) {
        StringBuffer sb = new StringBuffer("");
        for (Map<String, String> map : parametersList) {
            for (String str : map.keySet()) {
                sb.append(str.substring(0, 1));
                sb.append("_");
                sb.append(map.get(str));
                sb.append("&");
            }
        }
        return sb.toString();
    }

    /**
     * 由于 西班牙语、葡萄牙语、汉语 有 两种以上语言标识
     * 需要区分客户端上传的是否包含 r
     * 以及解决 没有上传区域 的默认语言问题
     *
     * @param request
     * @return
     */
    public static String getLanguage(HttpServletRequest request) {

        String language = request.getHeader("language");
        String locale = request.getHeader("locale");

        if (null == language) {
            return null;
        }

        if (language.equals("zh")) {
            if (null == locale) {
                return "zh-rCN";
            }
            if (locale.contains("r")) {
                return language + "-" + locale;
            } else {
                return language + "-r" + locale;
            }
        }

        if (language.equals("es")) {
            if (null == locale || locale.equals("ES")) {
                return "es-ES";
            }
            if (locale.contains("r")) {
                return language + "-" + locale;
            } else {
                return language + "-r" + locale;
            }
        }

        if (language.equals("pt")) {
            if (null == locale) {
                return "pt-BR";
            }
            return language + "-" + locale;
        }
        return language;
    }
}
