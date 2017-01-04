package com.rest.restapi.bo;

/**
 * <br>订单业务对象</br>
 *
 * @Class OrderBo
 * @Author lennylv
 * @Date 2017-1-4 15:19
 * @Version 1.0
 * @Since 1.0
 */
public class OrderBo {

    private int orderId;

    private String number;

    private int userId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
