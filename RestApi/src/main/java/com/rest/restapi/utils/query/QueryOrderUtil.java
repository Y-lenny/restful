package com.rest.restapi.utils.query;

import com.rest.restapi.utils.RegexUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>针对于order处理的工具类</br>
 * <p>
 * - 表示倒序，默认是升序
 * 使用关键字 sort
 * 例如：按照用户名升序、email倒序 ：order=username,-email
 * <p>
 * @Class QueryOrderUtil
 * @Author lennylv
 * @Date 2017-1-5 11:32
 * @Version 1.0
 * @Since 1.0
 */
public final class QueryOrderUtil {

    /**
     * - order=username,-email
     */
    private static final String ORDER_PATTERN = "((-?[a-zA-Z]+),?)*";

    private QueryOrderUtil() {
        throw new UnsupportedOperationException();
    }


    /**
     * <br>TODO 校验参数：校验排序参数是否有问题，应该维护哪些参数进行排序以及排序规则</br>
     *
     * @Method
     * @Param
     * @Return
     * @Exception
     * @Author lennylv
     * @Date 2017-1-5 14:08
     * @Version 1.0
     * @Since 1.0
     */
    public static boolean validateSort(List<Sort> sorts) {
        return true;
    }

    /**
     * <br>解析排序</br>
     *
     * @Method
     * @Param
     * @Return
     * @Exception
     * @Author lennylv
     * @Date 2017-1-5 14:36
     * @Version 1.0
     * @Since 1.0
     */
    public static List<Sort> parseSort(String sorts) {

        if (StringUtils.isEmpty(sorts) || !RegexUtil.isMatch(sorts, ORDER_PATTERN)) {
            return null;
        }

        String[] sortArr = sorts.split(QueryConstants.SEPARATOR);
        if (ArrayUtils.isEmpty(sortArr)) {
            return null;
        }

        ArrayList sortsList = new ArrayList<Sort>(sortArr.length);
        for (String sort : sortArr) {
            boolean descending = sort.startsWith("-");
            String fieldName = sort.replaceAll("^[+-]{1}", "");
            sortsList.add(new Sort(descending, fieldName));
        }

        return sortsList;
    }
}
