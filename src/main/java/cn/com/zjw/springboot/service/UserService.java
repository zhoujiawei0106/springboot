package cn.com.zjw.springboot.service;

import cn.com.zjw.springboot.entity.User;

public interface UserService {

    public User getUser(String loginName);
}
