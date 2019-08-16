package cn.com.zjw.springboot.service.impl;

import cn.com.zjw.springboot.entity.User;
import cn.com.zjw.springboot.mapper.UserMapper;
import cn.com.zjw.springboot.service.UserService;
import cn.com.zjw.springboot.utils.BlowfishCipher;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void save(User user) throws Exception{
        check(user);

        if (userMapper.getUser(user.getLoginName()) != null) {
            throw new Exception("登录名已存在,请重新输入");
        }

        user.setPassword(BlowfishCipher.encode(user.getPassword()));

        logger.info("新增用户信息-----" + user.toString());
        userMapper.save(user);
        logger.info("用户信息新增成功");
    }

    @Override
    public User getUser(User user) throws Exception {
        if (StringUtils.isBlank(user.getId())) {
            throw new Exception("用户代码不能为空");
        }

        logger.info("获取修改的用户信息-----" + user.toString());
        List<User> list= userMapper.getUsers(user);

        if (list.size() != 1) {
            logger.error("获取的用户数据不唯一:" + list.size());
            throw new Exception("");
        }

        user = list.get(0);
        user.setPassword(BlowfishCipher.decode(user.getPassword()));
        logger.info("用户信息-----" + user.toString());
        return user;
    }

    @Override
    public void update(User user) throws Exception {
        check(user);
        if (StringUtils.isBlank(user.getId())) {
            throw new Exception("用户代码不能为空");
        }

        user.setPassword(BlowfishCipher.encode(user.getPassword()));

        logger.info("修改用户信息-----" + user.toString());
        userMapper.update(user);
        logger.info("用户信息修改成功");
    }

    private final void check(User user) throws Exception {
        if (StringUtils.isBlank(user.getUserName())) {
            throw new Exception("请输入用户名");
        }
        if (StringUtils.isBlank(user.getLoginName())) {
            throw new Exception("请输入登录名");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            throw new Exception("请输入密码");
        }
        if (user.getTel() == null || user.getTel().toString().length() != 11) {
            throw new Exception("请输入正确的手机号码");
        }
    }
}
