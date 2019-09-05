package cn.com.zjw.springboot.mapper.system;

import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.entity.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 根据登陆用户获取用户信息
     * @author zhoujiawei
     * @param loginName
     * @return
     */
    public User getUser(@Param("loginName") String loginName);

    /**
     * 更新用户登陆次数
     * @author zhoujiawei
     * @param loginName
     * @param loginTimes
     */
    public void updateLoginTimes(@Param("user") User user);

    /**
     * 获取所有用户
     * @author zhoujiawei
     * @return
     */
    public List<User> getUsers(@Param("user") User user);

    /**
     * 新增用户
     * @author zhoujiawei
     * @param user
     */
    public void save(@Param("user") User user);

    /**
     * 修改用户信息
     * @author zhoujiawei
     * @param user
     */
    public void update(@Param("user") User user);

    /**
     * 删除用户
     * @author zhoujiawei
     * @param id
     */
    public void delete(@Param("id") String id);

    /**
     * 重置用户登录次数
     * @author zhoujiawei
     * @param id
     */
    public void reset(@Param("id") String id);

    /**
     * 根据客户信息修改用户信息
     * @author zhoujiawei
     * @param customer
     */
    public void updateByCustomer(@Param("customer") Customer customer);
}
