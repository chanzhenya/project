package com.bgy.device.entity;

import javax.persistence.*;

@Table(name = "sys_token")
public class SysToken {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    private String token;

    public SysToken(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }
}