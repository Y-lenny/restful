package com.rest.restapi.vo;

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

    private int orderId;

    private String number;

    private int userId;

    private UserVo userVo;

    public OrderVo() {
    }

    public OrderVo(int orderId, String number, int userId, UserVo userVo) {
        this.orderId = orderId;
        this.number = number;
        this.userId = userId;
        this.userVo = userVo;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
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

    @Override
    public String toString() {
        return "OrderVo{" +
                "orderId=" + orderId +
                ", number='" + number + '\'' +
                ", userId=" + userId +
                '}';
    }
}
