package com.rest.restapi.vo;

/**
 * @author : feelingxu@tcl.com
 * @DESC 标题
 * @department : 应用产品中心/JAVA工程师
 * @date : 2016-12-15
 * @since : 1.0.0
 */
public class JsonResult {
    private String code;
    private String msg;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(String retCode, String retMsg, Object data) {
        this.code = retCode;
        this.msg = retMsg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
