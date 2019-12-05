package cn.com.zjw.springboot.mapper.system;

import cn.com.zjw.springboot.dto.system.PermissionDto;
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
    public List<PermissionDto> getUndistributedRoles(@Param("userId") String userId,@Param("loginUser")  String loginUser);
}
