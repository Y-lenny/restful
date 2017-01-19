package com.rest.restapi.bean.po;

/**
 * <br>订单持久化对象</br>
 *
 * @Class OrderBo
 * @Author lennylv
 * @Date 2017-1-4 15:19
 * @Version 1.0
 * @Since 1.0
 */
public class OrderPo {

    /**
     * 订单唯一标识
     */
    private int orderId;

    /**
     * 订单编号
     */
    private String number;

    /**
     * 用户唯一标识
     */
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
