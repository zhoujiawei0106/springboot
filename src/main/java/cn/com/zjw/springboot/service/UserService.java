package cn.com.zjw.springboot.service;

import cn.com.zjw.springboot.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    /**
     * 根据登陆用户获取用户信息
     * @author zhoujiawei
     * @param loginName
     * @return
     */
    public User getUser(String loginName);

    /**
     * 根据登陆用户修改登陆次数
     * @author zhoujiawei
     * @param user
     */
    public void updateLoginTimes(User user);

    /**
     * 获取所有用户
     * @author zhoujiawei
     * @return
     */
    public PageInfo getUsers(User user);
}
