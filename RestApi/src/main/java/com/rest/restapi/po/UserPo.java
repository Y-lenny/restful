package com.rest.restapi.po;

import java.io.Serializable;

/**
 * <br>用户持久化对象</br>
 *
 * @Class UserBo
 * @Author lennylv
 * @Date 2017-1-4 12:46
 * @Version 1.0
 * @Since 1.0
 */
public class UserPo implements Serializable {

    private int userId;

    private String username;

    private String email;

    private String phoneNumber;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
