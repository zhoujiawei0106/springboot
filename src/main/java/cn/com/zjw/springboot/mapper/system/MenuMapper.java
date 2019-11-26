package cn.com.zjw.springboot.mapper.system;

import cn.com.zjw.springboot.entity.system.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    /**
     * 根据登陆用户获取菜单
     * @author zhoujiawei
     * @param userId
     * @return
     */
    public List<Menu> getUserMenu(@Param("userId") String userId);

    /**
     * 获取用户所有可分配的菜单
     * @author zhoujiawei
     * @param userId
     * @param roleId
     * @param type 操作类型，新增查询还是修改查询
     * @param flag 超级用户为真，普通用户为假
     * @return
     */
    public List getUndistributedMenu(@Param("userId") String userId, @Param("roleId") String roleId,
                                     @Param("type") String type, @Param("flag") boolean flag);
}
