package com.rest.restapi.util.query;

/**
 * Created by lennylv on 2017-1-5.
 */
public enum SearchMiddle {
    EQ, NEG_EQ, CONTAINS, NEG_CONTAINS, STARTS_WITH, NEG_STARTS_WITH, ENDS_WITH, NEG_ENDS_WITH;

    public static boolean isNegated(SearchMiddle middle) {
        switch (middle) {
            case EQ:
                return false;
            case NEG_EQ:
                return true;
            case CONTAINS:
                return false;
            case NEG_CONTAINS:
                return true;
            case STARTS_WITH:
                return false;
            case NEG_STARTS_WITH:
                return true;
            case ENDS_WITH:
                return false;
            case NEG_ENDS_WITH:
                return true;
            default:
                return false;
        }
    }
}
