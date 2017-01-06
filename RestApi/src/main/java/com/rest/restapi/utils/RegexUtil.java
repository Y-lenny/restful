package com.rest.restapi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br>正则表达式匹配工具类</br>
 *
 * @Class RegexUtil
 * @Author lennylv
 * @Date 2016/10/17 10:13
 * @Version 1.0
 * @Since 1.0
 */
public final class RegexUtil {

    // 私有构造
    private RegexUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * <br>正则表达式匹配，若匹配有结果则返回匹配内容，则无结果则返回null</br>
     *
     * @Method
     * @Param
     * @Return
     * @Exception
     * @Author lennylv
     * @Date 2016/10/17 10:13
     * @Version 1.0
     * @Since 1.0
     */
    public static String matchRegex(String matchSource, String regexExpression) {

        return matchRegex(matchSource, regexExpression, 0);
    }

    /**
     * <br>是否可以匹配</br>
     *
     * @Method
     * @Param
     * @Return
     * @Exception
     * @Author lennylv
     * @Date 2016/10/17 10:14
     * @Version 1.0
     * @Since 1.0
     */
    public static boolean isMatch(String matchSource, String regexExpression) {

        Pattern pattern = Pattern.compile(regexExpression);
        Matcher matcher = pattern.matcher(matchSource);
        return matcher.matches();
    }

    /**
     * <br>带组信息的正则表达式匹配</br>
     *
     * @Method
     * @Param
     * @Return
     * @Exception
     * @Author lennylv
     * @Date 2016/10/17 10:14
     * @Version 1.0
     * @Since 1.0
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
