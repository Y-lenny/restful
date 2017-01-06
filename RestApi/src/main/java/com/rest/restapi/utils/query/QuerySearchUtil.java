package com.rest.restapi.utils.query;

import com.rest.restapi.utils.RegexUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class QuerySearchUtil {


    /**
     * - id=1,xxid=1,xxname=0-9*
     */
    private static final String SEARCH_PATTERN = "((id~?=[0-9]+)?,?) * ((name~?=[0-9a-zA-Z\\-_/*]+),?)*";

    private QuerySearchUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * <br>验证参数</br>
     *
     * @Method
     * @Param
     * @Return
     * @Exception
     * @Author lennylv
     * @Date 2017-1-5 14:18
     * @Version 1.0
     * @Since 1.0
     */
    public static boolean validateSearch(String paramKeys) {
        return true;
    }

    /**
     * <br>解析搜索</br>
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
    public static List<Search> parseSearch(final String searches) {

        if (!StringUtils.isEmpty(searches) || !RegexUtil.isMatch(searches, SEARCH_PATTERN)) {
            return null;
        }
        String[] searchArr = searches.split(QueryConstants.SEPARATOR);
        if (ArrayUtils.isEmpty(searchArr)) {
            return null;
        }

        List<Search> searchList = new ArrayList<Search>(searchArr.length);
        for (String search : searchArr) {
            String[] keyAndValue = search.split(QueryConstants.OP);
            if (ArrayUtils.isEmpty(keyAndValue) || keyAndValue.length != 2) {
                continue;
            }
            searchList.add(createSearch(keyAndValue[0], keyAndValue[1]));
        }

        return searchList;
    }

    private static Search createSearch(final String key, final String value) {

        boolean negated = false;
        if (key.endsWith(QueryConstants.NEGATION)) {
            negated = true;
        }
        SearchMiddle op = determineOperation(negated, value);
        String theKey = determineKey(negated, key);
        String theValue = determineValue(value);

        return new Search(theKey, op, theValue);
    }

    private static SearchMiddle determineOperation(final boolean negated, final String value) {
        SearchMiddle op = null;
        if (value.startsWith(QueryConstants.ANY_CLIENT)) {
            if (value.endsWith(QueryConstants.ANY_CLIENT)) { // id ~= *xx* -> 表示不包含， id = *xx* -> 表示包含，
                op = negated ? SearchMiddle.NEG_CONTAINS : SearchMiddle.CONTAINS;
            } else {// id ~= *xx -> 表示不以此作为终结点， id = *xx -> 表示以此作为终结点
                op = negated ? SearchMiddle.NEG_ENDS_WITH : SearchMiddle.ENDS_WITH;
            }
        } else if (value.endsWith(QueryConstants.ANY_CLIENT)) {// id ~= xx* -> 表示不以此作为起始结点， id = *xx -> 表示以此作为起始结点
            op = negated ? SearchMiddle.NEG_STARTS_WITH : SearchMiddle.STARTS_WITH;
        } else {// id ~= xx -> 表示不等于， id = xx -> 表示等于
            op = negated ? SearchMiddle.NEG_EQ : SearchMiddle.EQ;
        }
        return op;
    }

    private static String determineValue(final String value) {
        return value.replaceAll("\\*", QueryConstants.ANY_SERVER);
    }

    private static String determineKey(final boolean negated, final String key) {
        if (negated) {
            return key.substring(0, key.length() - 1);
        }
        return key;
    }

}
