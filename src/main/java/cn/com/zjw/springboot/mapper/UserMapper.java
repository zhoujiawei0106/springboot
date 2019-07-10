package cn.com.zjw.springboot.mapper;

import cn.com.zjw.springboot.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 根据登陆用户获取用户信息
     * @author zhoujiawei
     * @param loginName
     * @return
     */
    public User getUser(@Param("loginName") String loginName);
}
