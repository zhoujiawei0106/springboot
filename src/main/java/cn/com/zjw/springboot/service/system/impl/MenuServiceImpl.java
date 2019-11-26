package cn.com.zjw.springboot.service.system.impl;

import cn.com.zjw.springboot.constants.CodeConstants;
import cn.com.zjw.springboot.dto.system.MenuDto;
import cn.com.zjw.springboot.dto.system.PermissionMenu;
import cn.com.zjw.springboot.entity.system.Menu;
import cn.com.zjw.springboot.entity.system.User;
import cn.com.zjw.springboot.mapper.system.MenuMapper;
import cn.com.zjw.springboot.mapper.system.UserMapper;
import cn.com.zjw.springboot.service.system.MenuService;
import cn.com.zjw.springboot.utils.PinyinUtils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Menu> getUserMenu(String userId) {
        return menuMapper.getUserMenu(userId);
    }

    @Override
    public List<PermissionMenu> getUndistributedMenu(String userId, String type, String roleId) throws Exception {
        // 根据token中获取的用户获取登陆用户信息
        User user = userMapper.getUserById(userId);
        if (user == null) {
            throw new Exception("为查询到相关登陆用户，请重新登陆或联系管理员");
        }

        // 根据登陆用户类型，获取用户可分配的菜单
        List<PermissionMenu> menuList = new ArrayList<>();
        if (CodeConstants.USER_TYPE_ADMIN.equals(user.getUserType())) {
            menuList = menuMapper.getUndistributedMenu(userId, roleId, type, false);
        } else if (CodeConstants.USER_TYPE_NORMAL.equals(user.getUserType())) {
            menuList = menuMapper.getUndistributedMenu(userId, roleId, type, true);
        } else {
            throw new Exception("用户类型不匹配，请联系管理员");
        }

        List<PermissionMenu> copyMenus = new ArrayList<>();
        copyMenus.addAll(menuList);
        // 所有一级菜单
        List<PermissionMenu> addList = new ArrayList<>();
        for (PermissionMenu permissionMenu : menuList) {
            if (StringUtils.isBlank(permissionMenu.getPid())) {
                permissionMenu.setPid(UUID.randomUUID().toString());
                permissionMenu.setType(PinyinUtils.toPinyin(permissionMenu.getLabel()));

                addList.add(permissionMenu);

                copyMenus.remove(permissionMenu);

                recursionMenu(copyMenus, addList, permissionMenu);
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
    private List<PermissionMenu> recursionMenu(List<PermissionMenu> list, List<PermissionMenu> addList,
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
