package cn.com.zjw.springboot.service.system;

import cn.com.zjw.springboot.entity.system.Role;
import com.github.pagehelper.PageInfo;

public interface RoleService {

    /**
     * 获取角色
     * @author zhoujiawei
     * @param role
     * @return
     */
    public PageInfo getRoles(Role role);
}
