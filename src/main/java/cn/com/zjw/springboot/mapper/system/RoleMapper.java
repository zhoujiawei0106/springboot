package cn.com.zjw.springboot.mapper.system;

import cn.com.zjw.springboot.entity.system.Role;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

    /**
     * 插入角色信息
     * @author zhoujiawei
     * @param role
     * @param userId
     */
    public void insert(@Param("role") Role role, @Param("userId") String userId);

    /**
     * 查询权限数据
     * @author zhoujiawei
     * @param id
     */
    public void insertPermission(@Param("id") String id);

    /**
     * 插入角色权限数据
     * @author zhoujiawei
     * @param list
     * @param roleId
     */
    public void insertRolePermission(@Param("list") List<JSONObject> list, @Param("roleId") String roleId,
                                     @Param("permissionId") String permissionId);

    /**
     * 根据roleId获取角色信息
     * @author zhoujiawei
     * @param roleId
     * @param userId
     * @return
     */
    public Role getByRoleIdAndUserId(@Param("roleId") String roleId, @Param("userId") String userId);

    /**
     * 根据角色id修改角色信息
     * @author zhoujiawei
     * @param role
     */
    public void update(@Param("role") Role role);

    /**
     * 根据角色id获取权限id
     * @author zhoujiawei
     * @param roleId
     * @return
     */
    public String getPermissionId(@Param("roleId") String roleId);

    /**
     * 删除角色权限相应的数据
     * @author zhoujiawei
     * @param roleId
     * @param permissionId
     */
    public void deleteRolePermission(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
