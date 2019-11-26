package cn.com.zjw.springboot.service.system;

import cn.com.zjw.springboot.dto.system.PermissionMenu;
import cn.com.zjw.springboot.entity.system.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 根据登陆用户获取菜单
     * @author zhoujiawei
     * @param userId
     */
    public List<Menu> getUserMenu(String userId);

    /**
     * 根据登陆用户获取未分配的菜单
     * @author zhoujiawei
     * @param userId
     * @param type 操作类型，新增查询还是修改查询
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<PermissionMenu> getUndistributedMenu(String userId, String type, String roleId) throws Exception;
}
