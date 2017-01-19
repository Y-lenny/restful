package com.rest.restapi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br>正则表达式匹配工具类</br>
 *
 * @class RegexUtil
 * @author lennylv
 * @date 2016/10/17 10:13
 * @version 1.0
 * @since 1.0
 */
public final class RegexUtil {

    // 私有构造
    private RegexUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * <br>正则表达式匹配，若匹配有结果则返回匹配内容，则无结果则返回null</br>
     *
     * @param     [matchSource, regexExpression]
     * @return    java.lang.String
     * @exception
     * @author    lennylv
     * @date      2017-1-16 15:20
     * @version   1.0
     * @since     1.0
     */
    public static String matchRegex(String matchSource, String regexExpression) {

        return matchRegex(matchSource, regexExpression, 0);
    }

    /**
     * <br>是否可以匹配</br>
     *
     * @param     [matchSource, regexExpression]
     * @return    boolean
     * @exception
     * @author    lennylv
     * @date      2017-1-16 15:20
     * @version   1.0
     * @since     1.0
     */
    public static boolean isMatch(String matchSource, String regexExpression) {

        Pattern pattern = Pattern.compile(regexExpression);
        Matcher matcher = pattern.matcher(matchSource);
        return matcher.matches();
    }

    /**
     * <br>带组信息的正则表达式匹配</br>
     *
     * @param     [matchSource, regexExpression, gourpIndex]
     * @return    java.lang.String
     * @exception
     * @author    lennylv
     * @date      2017-1-16 15:20
     * @version   1.0
     * @since     1.0
     */
    public static String matchRegex(String matchSource, String regexExpression, int gourpIndex) {

        Pattern pattern = Pattern.compile(regexExpression);
        Matcher matcher = pattern.matcher(matchSource);
        String matchResult = null;
        if (matcher.find()) {
            matchResult = matcher.group(gourpIndex);
        }
        return matchResult;
    }
}
