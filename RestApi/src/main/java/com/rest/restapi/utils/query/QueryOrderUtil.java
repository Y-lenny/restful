package com.rest.restapi.utils.query;

import com.rest.restapi.utils.RegexUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>针对于order处理的工具类</br>
 *
 * @Class QueryOrderUtil
 * @Author lennylv
 * @Date 2017-1-5 11:32
 * @Version 1.0
 * @Since 1.0
 */
public final class QueryOrderUtil {

    /**
     * - order=+username,-email
     */
    private static final String ORDER_PATTERN = "([+|-]{1}[a-zA-Z]+,)*([+|-]{1}[a-zA-Z]+)";

    private QueryOrderUtil() {
        throw new UnsupportedOperationException();
    }


    /**
     * <br>校验参数</br>
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

        if (!StringUtils.isEmpty(sorts) || !RegexUtil.isMatch(sorts, ORDER_PATTERN )) {
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
