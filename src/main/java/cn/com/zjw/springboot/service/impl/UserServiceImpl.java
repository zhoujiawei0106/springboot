package cn.com.zjw.springboot.service.impl;

import cn.com.zjw.springboot.entity.User;
import cn.com.zjw.springboot.mapper.UserMapper;
import cn.com.zjw.springboot.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String loginName) {
        return userMapper.getUser(loginName);
    }

    @Override
    public void updateLoginTimes(User user) {
        logger.info("更新用户登陆次数-----" + user.toString());
        userMapper.updateLoginTimes(user);
    }

    @Override
    public PageInfo getUsers(User user) {
        PageHelper.startPage(user.getPage(), user.getRows());
        logger.info("根据条件查询所有用户----" + user.toString());
        List<User> list = userMapper.getUsers(user);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void save(User user) {
        // TODO 密码加密
        user.setId(UUID.randomUUID().toString());

        logger.info("新增用户信息-----" + user.toString());
        userMapper.save(user);
    }

    private static final void check(User user) throws Exception {
        if (StringUtils.isBlank(user.getUserName())) {
            throw new Exception("");
        }
        if (StringUtils.isBlank(user.getUserName())) {
            throw new Exception("");
        }
        if (StringUtils.isBlank(user.getUserName())) {
            throw new Exception("");
        }
    }
}
