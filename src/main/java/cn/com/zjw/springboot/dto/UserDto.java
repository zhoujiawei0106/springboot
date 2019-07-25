package cn.com.zjw.springboot.dto;

import cn.com.zjw.springboot.entity.User;

import java.io.Serializable;

public class UserDto extends User implements Serializable {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
