package cn.com.zjw.springboot.service;

import cn.com.zjw.springboot.entity.User;

public interface UserService {

    /**
     * 根据登陆用户获取用户信息
     * @author zhoujiawei
     * @param loginName
     * @return
     */
    public User getUser(String loginName);
}
