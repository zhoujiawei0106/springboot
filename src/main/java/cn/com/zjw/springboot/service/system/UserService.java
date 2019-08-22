package cn.com.zjw.springboot.service.system;

import cn.com.zjw.springboot.entity.system.User;
import com.github.pagehelper.PageInfo;

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

    /**
     * 保存用户信息
     * @author zhoujiawei
     * @param user
     * @return
     */
    public void save(User user) throws Exception;

    /**
     * 获取用户
     * @author zhoujiawei
     * @param user
     * @return
     */
    public User getUser(User user) throws Exception;

    /**
     * 更新用户信息
     * @author zhoujiawei
     * @param user
     */
    public void update(User user) throws Exception;

    /**
     * 删除用户
     * @author zhoujiawei
     * @param id
     */
    public void delete(String id) throws Exception;

    /**
     * 重置用户登陆次数
     * @author zhoujiawei
     * @param id
     */
    public void reset(String id) throws Exception;
}
