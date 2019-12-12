package cn.com.zjw.springboot.mapper.system;

import cn.com.zjw.springboot.dto.system.PermissionDto;
import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {

    /**
     * 根据主键查询权限记录数量
     * @author zhoujiawei
     * @param id
     * @return
     */
    public int count(@Param("id") String id);

    /**
     * 根据主键删除权限记录
     * @author zhoujiawei
     * @param id
     */
    public void delete(@Param("id") String id);

    /**
     * 获取
     * @author zhoujiawei
     * @param userId
     * @param loginUser
     * @return
     */
    /*public List<PermissionDto> getUndistributedRoles(@Param("userId") String userId,@Param("loginUser")  String loginUser);*/

    /**
     * 权限分配
     * @param userId
     * @param permissionId
     */
    public void saveUserPermission(@Param("userId") String userId,@Param("permissionIdList") List<PermissionDto> permissionId);

    /**
     * 判断用户权限是否存在
     * @param userId
     */
    public Integer getUserPermission(String userId);

    /**
     * 删除权限的关联
     * @param userId
     * @param userId
     */
    public void deleteUserPermission(String userId);

    /**
     * 通过权限查询对应的角色id
     * @param role
     */
    public List<PermissionDto> getPermissionByRoleId(@Param("role") JSONArray role);


}
