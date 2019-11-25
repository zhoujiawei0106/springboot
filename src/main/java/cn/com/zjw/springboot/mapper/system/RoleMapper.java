package cn.com.zjw.springboot.mapper.system;

import cn.com.zjw.springboot.entity.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    /**
     * 获取用户角色
     * @author zhoujiawei
     * @param role
     * @return
     */
    public List<Role> getRoles(@Param("role") Role role);
}
