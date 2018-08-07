package cn.com.zjw.springboot.mapper;

import cn.com.zjw.springboot.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    public User getUser(@Param("loginName") String loginName);
}
