package cn.com.zjw.springboot.controller;

import cn.com.zjw.springboot.dto.MenuDto;
import cn.com.zjw.springboot.entity.Menu;
import cn.com.zjw.springboot.entity.User;
import cn.com.zjw.springboot.service.MenuService;
import cn.com.zjw.springboot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 登陆
 */
@RestController
public class LoginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    /**
     * 用户登陆
     * @author zhoujiawei
     * @param loginName
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(String loginName, String password) {
        try {
            if (StringUtils.isBlank(loginName)) {
                return fail("请输入用户名");
            }
            if (StringUtils.isBlank(password)) {
                return fail("请输入密码");
            }

            // 根据登陆用户获取用户信息
            User user = userService.getUser(loginName);

            if (user == null) {
                return fail("用户名或密码错误!");
            }
            // TODO 考虑如何重置登陆次数，如何实现10分钟后重置登陆次数
            if (user.getLoginFailTimes() >= 3) {
                return fail("用户登陆次数超过3次，请联系管理员");
            }
            if (!password.equals(user.getPassword())) {
                return fail("用户名或密码错误");
            }

            // 返回用户的菜单
            List<Menu> menuList = menuService.getUserMenu(user.getId());
            // 复制返回用户的菜单
            List<Menu> coypMenus = new ArrayList<>();
            coypMenus.addAll(menuList);
            // 所有一级菜单
            List<MenuDto> addList = new ArrayList<>();
            for (Menu menu : menuList) {
                if (StringUtils.isBlank(menu.getPid())) {
                    MenuDto menuDto = new MenuDto();
                    menuDto.setId(menu.getId());
                    menuDto.setName(menu.getMenuName());
                    addList.add(menuDto);

                    coypMenus.remove(menu);

                    recursionMenu(coypMenus, addList, menuDto, menu.getId());
                }
            }

            return success(addList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 递归生成正确的菜单树
     * @author zhoujiawei
     * @param list 未设置的菜单集合
     * @param addList 设置层的菜单集合
     * @param menuDto 设置层的菜单对象
     * @param menuId 设置层的菜单id
     * @return
     */
    private List<MenuDto> recursionMenu(List<Menu> list, List<MenuDto> addList, MenuDto menuDto, String menuId) {
        List<Menu> copyMenus = new ArrayList<>();
        copyMenus.addAll(list);
        for (Menu menu : list) {
            if (menuId.equals(menu.getPid())) {
                List<MenuDto> subMenus = menuDto.getSubMenus();
                MenuDto subMenuDto = new MenuDto();
                subMenuDto.setId(menu.getId());
                subMenuDto.setName(menu.getMenuName());
                subMenuDto.setUrl(menu.getMenuRoutePath());
                subMenus.add(subMenuDto);
                menuDto.setSubMenus(subMenus);

                copyMenus.remove(menu);

                recursionMenu(copyMenus, subMenus, subMenuDto, menu.getId());

            }
        }
        return addList;
    }


}
