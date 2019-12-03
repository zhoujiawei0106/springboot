package cn.com.zjw.springboot.service.system;

import cn.com.zjw.springboot.entity.system.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {

    /**
     * 获取角色
     * @author zhoujiawei
     * @param role
     * @return
     */
    public List<Role> getRoles(Role role);

    /**
     * 保存角色
     * @author zhoujiawei
     * @param role
     * @param menus
     * @param userId
     * @throws
     */
    public void save(Role role, String menus, String userId) throws Exception;

    /**
     * 根据用户id和角色id获取角色信息
     * @author zhoujiawei
     * @param roleId
     * @param userId
     * @return
     */
    public Role getByRoleIdAndUserId(String roleId, String userId) throws Exception;

    /**
     * 修改用户角色
     * @author zhoujiawei
     * @param role
     * @param menus
     * @param userId
     * @throws
     */
    public void update(Role role, String menus, String userId) throws Exception;
}
