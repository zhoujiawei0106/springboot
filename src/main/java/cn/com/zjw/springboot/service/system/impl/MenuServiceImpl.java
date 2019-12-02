package cn.com.zjw.springboot.service.system.impl;

import cn.com.zjw.springboot.constants.CodeConstants;
import cn.com.zjw.springboot.constants.enumConstants.ValidStatus;
import cn.com.zjw.springboot.dto.system.PermissionMenu;
import cn.com.zjw.springboot.entity.system.Menu;
import cn.com.zjw.springboot.entity.system.User;
import cn.com.zjw.springboot.mapper.system.MenuMapper;
import cn.com.zjw.springboot.mapper.system.UserMapper;
import cn.com.zjw.springboot.service.system.MenuService;
import cn.com.zjw.springboot.utils.PinyinUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;

    // 缓存首层菜单的id-pid
    private static Map<String, String> map = new LinkedHashMap<>();

    @Override
    public List<Menu> getUserMenu(String userId) {
        return menuMapper.getUserMenu(userId, ValidStatus.Valid.getValue());
    }

    @Override
    public List<PermissionMenu> getUndistributedMenu(String userId, String type, String roleId) throws Exception {
        // 每次重新加载左边菜单时，清楚缓存中的首层菜单的id-pid
        map = new LinkedHashMap<>();

        // 根据token中获取的用户获取登陆用户信息
        User user = userMapper.getUserById(userId);
        if (user == null) {
            throw new Exception("为查询到相关登陆用户，请重新登陆或联系管理员");
        }

        // 根据登陆用户类型，获取用户可分配的菜单
        List<PermissionMenu> menuList = new ArrayList<>();
        if (CodeConstants.USER_TYPE_ADMIN.equals(user.getUserType())) {
            menuList = menuMapper.getUndistributedMenu(userId, roleId, type, false, ValidStatus.Valid.getValue());
        } else if (CodeConstants.USER_TYPE_NORMAL.equals(user.getUserType())) {
            if (StringUtils.isNotEmpty(roleId)) {
                throw new Exception("角色代码不能为空");
            }
            menuList = menuMapper.getUndistributedMenu(userId, roleId, type, true, ValidStatus.Valid.getValue());
        } else {
            throw new Exception("用户类型不匹配，请联系管理员");
        }

        List<PermissionMenu> copyMenus = new ArrayList<>();
        copyMenus.addAll(menuList);
        // 所有一级菜单
        List<PermissionMenu> addList = new ArrayList<>();
        firstFloorMenu(menuList, addList, copyMenus);
//        for (PermissionMenu permissionMenu : menuList) {
//            if (StringUtils.isBlank(permissionMenu.getPid())) {
//                permissionMenu.setPid(UUID.randomUUID().toString());
//                permissionMenu.setType(PinyinUtils.toPinyin(permissionMenu.getLabel()));
//
//                addList.add(permissionMenu);
//
//                copyMenus.remove(permissionMenu);
//
//                recursionMenu(copyMenus, addList, permissionMenu);
//
//                // 递归结束后如果最外层菜单的子菜单为空，不展示首层菜单
//                if (permissionMenu.getChildren().size() == 0) {
//                    addList.remove(permissionMenu);
//                } else {
//                    map.put(permissionMenu.getId(), permissionMenu.getPid());
//                }
//            }
//        }
        return addList;
    }

    @Override
    public List<PermissionMenu> getDistributeMenu(String userId, String roleId) throws Exception {
        if (StringUtils.isBlank(roleId)) {
            throw new Exception("角色代码不能为空");
        }
        // 根据token中获取的用户获取登陆用户信息
        User user = userMapper.getUserById(userId);
        if (user == null) {
            throw new Exception("为查询到相关登陆用户，请重新登陆或联系管理员");
        }

        // 根据登陆用户类型，获取用户可分配的菜单
        List<PermissionMenu> menuList = menuMapper.getDistributeMenu(userId, roleId, ValidStatus.Valid.getValue());
        List<PermissionMenu> copyMenus = new ArrayList<>();
        copyMenus.addAll(menuList);
        // 所有一级菜单
        List<PermissionMenu> addList = new ArrayList<>();
        firstFloorMenu(menuList, addList, copyMenus);

        return addList;
    }

    private final List<PermissionMenu> firstFloorMenu(List<PermissionMenu> menuList, List<PermissionMenu> addList,
                                                      List<PermissionMenu> copyMenus) throws Exception {
        for (PermissionMenu permissionMenu : menuList) {
            if (StringUtils.isBlank(permissionMenu.getPid())) {
                permissionMenu.setPid(UUID.randomUUID().toString());
                permissionMenu.setType(PinyinUtils.toPinyin(permissionMenu.getLabel()));

                addList.add(permissionMenu);

                copyMenus.remove(permissionMenu);

                recursionMenu(copyMenus, addList, permissionMenu);

                // 递归结束后如果最外层菜单的子菜单为空，不展示首层菜单
                if (permissionMenu.getChildren().size() == 0) {
                    addList.remove(permissionMenu);
                } else {
                    map.put(permissionMenu.getId(), permissionMenu.getPid());
                }
            }
        }
        return addList;
    }

    /**
     * 递归生成正确的菜单树
     * @author zhoujiawei
     * @param list 未设置的菜单集合
     * @param addList 设置层的菜单集合
     * @param permissionMenu 设置层的菜单对象
     * @return
     * @throws
     */
    private final List<PermissionMenu> recursionMenu(List<PermissionMenu> list, List<PermissionMenu> addList,
                                               PermissionMenu permissionMenu) throws Exception {
        List<PermissionMenu> copyMenus = new ArrayList<>();
        copyMenus.addAll(list);
        for (PermissionMenu menu : list) {
            if (permissionMenu.getId().equals(menu.getPid())) {
                List<PermissionMenu> subMenus = permissionMenu.getChildren();
                PermissionMenu subMenu = new PermissionMenu();
                subMenu.setId(menu.getId());
                subMenu.setLabel(menu.getLabel());
                subMenu.setPid(menu.getPid());
                subMenu.setType(PinyinUtils.toPinyin(menu.getLabel()));
                subMenus.add(subMenu);
                permissionMenu.setChildren(subMenus);

                copyMenus.remove(menu);

                recursionMenu(copyMenus, subMenus, menu);
            }
        }
        return addList;
    }
}
