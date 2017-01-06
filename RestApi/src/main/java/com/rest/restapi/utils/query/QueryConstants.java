package com.rest.restapi.utils.query;

public final class QueryConstants {

    private QueryConstants() {
        throw new AssertionError();
    }

    public static final String PAGE = "page";

    public static final String SIZE = "size";

    public static final String SORT = "sort";

    public static final String FIELD = "field";

    public static final String EMBED = "embed";

    public static final String Q_PARAM = "q";

    /**
     * - note: this character represents the ANY wildcard for the server side (persistence layer)
     */
    public static final String ANY_SERVER = "%";

    /**
     * - note: this character represents the ANY wildcard for the client consumption of the API
     */
    public static final String ANY_CLIENT = "*";


    public static final String SEPARATOR = ",";

    public static final String OP = "=";

    public static final String NEGATION = "~";

}
