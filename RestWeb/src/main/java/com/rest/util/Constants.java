package com.rest.util;

import java.util.concurrent.ConcurrentHashMap;

public abstract interface Constants {

    /**
     * 后台管理记录的状态标识符
     */
    public static enum Status {
        ENABLE, DISABLE, DELETE
    }

    /**
     * 管理后台操作记录方式
     */

    public static enum Operate {
        ADD, DELETE, ENABLE, DISABLE, UPDATE, SELECT, Operate, STATUS
    }

}
