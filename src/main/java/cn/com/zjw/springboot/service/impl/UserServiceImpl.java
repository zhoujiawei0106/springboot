package cn.com.zjw.springboot.service.impl;

import cn.com.zjw.springboot.entity.User;
import cn.com.zjw.springboot.mapper.UserMapper;
import cn.com.zjw.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String loginName) {
        return userMapper.getUser(loginName);
    }
}
