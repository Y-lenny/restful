package com.rest.bean.dto;

/**
 * Created by lennylv on 2017-1-20.
 */
public class UserDto {

    private long userId;
    private String username;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
