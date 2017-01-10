package com.rest.restapi.util.query;

/**
 * <br>排序实体</br>
 *
 * @Class Sort
 * @Author lennylv
 * @Date 2017-1-5 12:55
 * @Version 1.0
 * @Since 1.0
 */
public class Sort {

    private boolean descending;

    private String fieldName;

    public Sort() {
    }

    public Sort(boolean descending, String fieldName) {
        this.descending = descending;
        this.fieldName = fieldName;
    }

    public boolean isDescending() {
        return descending;
    }

    public void setDescending(boolean descending) {
        this.descending = descending;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
