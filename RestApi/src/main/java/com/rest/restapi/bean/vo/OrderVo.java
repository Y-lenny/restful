package com.rest.restapi.bean.vo;

/**
 * <br>订单web对象</br>
 *
 * @Class OrderBo
 * @Author lennylv
 * @Date 2017-1-4 15:19
 * @Version 1.0
 * @Since 1.0
 */
public class OrderVo {

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

    /**
     * 订单关联的用户
     */
    private UserVo userVo;

    public OrderVo() {
    }

    public OrderVo(int orderId, String number, int userId, UserVo userVo) {
        this.orderId = orderId;
        this.number = number;
        this.userId = userId;
        this.userVo = userVo;
    }

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

    public com.rest.restapi.bean.vo.UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(com.rest.restapi.bean.vo.UserVo userVo) {
        this.userVo = userVo;
    }
}
