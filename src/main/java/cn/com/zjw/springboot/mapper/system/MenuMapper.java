package cn.com.zjw.springboot.mapper.system;

import cn.com.zjw.springboot.dto.system.PermissionMenu;
import cn.com.zjw.springboot.entity.system.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    /**
     * 根据登陆用户获取菜单
     * @author zhoujiawei
     * @param userId
     * @param isValid
     * @return
     */
    public List<Menu> getUserMenu(@Param("userId") String userId, @Param("isValid") String isValid);

    /**
     * 获取用户所有可分配的菜单
     * @author zhoujiawei
     * @param userId
     * @param roleId
     * @param type 操作类型，新增查询还是修改查询
     * @param flag 超级用户为真，普通用户为假
     * @param isValid
     * @return
     */
    public List getUndistributedMenu(@Param("userId") String userId, @Param("roleId") String roleId,
                                     @Param("type") String type, @Param("flag") boolean flag,
                                     @Param("isValid") String isValid);

    /**
     * 根据角色获取已分配的菜单
     * @author zhoujiawei
     * @param userId
     * @param roleId
     * @param isValid
     * @return
     */
    public List<PermissionMenu> getDistributeMenu(@Param("userId") String userId, @Param("roleId") String roleId,
                                                  @Param("isValid") String isValid);

    /**
     * 获取未分配数据
     * @param userId
     * @param loginUser
     * @return
     */
    List<PermissionMenu> getUndistributedRoles(@Param("userId") String userId, @Param("loginUser") String loginUser);
    List<PermissionMenu> getDistributedRoles(@Param("userId") String userId, @Param("loginUser") String loginUser);
}
