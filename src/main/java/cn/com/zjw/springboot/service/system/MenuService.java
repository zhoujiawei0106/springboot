package cn.com.zjw.springboot.service.system;

import cn.com.zjw.springboot.entity.system.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 根据登陆用户获取菜单
     * @author zhoujiawei
     * @param userId
     */
    public List<Menu> getUserMenu(String userId);
}
