package cn.com.zjw.springboot.controller;

import cn.com.zjw.springboot.constants.CodeConstants;
import cn.com.zjw.springboot.dto.system.MenuDto;
import cn.com.zjw.springboot.dto.system.UserDto;
import cn.com.zjw.springboot.entity.system.Menu;
import cn.com.zjw.springboot.entity.system.User;
import cn.com.zjw.springboot.service.system.MenuService;
import cn.com.zjw.springboot.service.system.UserService;
import cn.com.zjw.springboot.utils.BlowfishCipher;
import cn.com.zjw.springboot.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static final Map<String, String> userMap = new HashMap<>();

    /**
     * 用户登陆
     * @author zhoujiawei
     * @param loginName
     * @param password
     * @return
     * @throws Exception
     */
    @GetMapping("/login/{loginName}/{password}")
    public Map<String, Object> login(HttpServletRequest request, @PathVariable("loginName") String loginName,
                                     @PathVariable("password") String password) {
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
            if (user.getLoginFailTimes() > 3) {
                return fail("用户已连续登陆次数超过3次，请联系管理员");
            }

            // 先跟新本次用户的ip
            user.setIp(getUserIp(request));

            if (!password.equals(BlowfishCipher.decode(user.getPassword()))) {
                // 密码错误更新登陆次数
                userService.updateLoginTimes(user);
                return fail("用户名或密码错误");
            }

            // 如果登陆成功，初始化登陆失败次数
            user.setLoginFailTimes(-1);
            userService.updateLoginTimes(user);

            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userDto.setToken(TokenUtils.createToken(CodeConstants.EXPIRE_TIME, user));

            // 缓存用户信息
            userMap.put(user.getId(), userDto.getToken());

            return success(userDto);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 获取用户菜单
     * @author zhoujiawei
     * @param loginName
     * @return
     */
    @GetMapping("/userMenu/{loginName}")
    public Map<String, Object> getUserMenu(@PathVariable("loginName") String loginName) {
        try {
            if (StringUtils.isBlank(loginName)) {
                return fail("系统异常，请联系管理员");
            }

            User user = userService.getUser(loginName);
            // 返回用户的菜单
            List<Menu> menuList = menuService.getUserMenu(user.getId());
            // 复制返回用户的菜单
            List<Menu> copyMenus = new ArrayList<>();
            copyMenus.addAll(menuList);
            // 所有一级菜单
            List<MenuDto> addList = new ArrayList<>();
            for (Menu menu : menuList) {
                if (StringUtils.isBlank(menu.getPid())) {
                    MenuDto menuDto = new MenuDto();
                    menuDto.setId(menu.getId());
                    menuDto.setName(menu.getMenuName());
                    addList.add(menuDto);

                    copyMenus.remove(menu);

                    recursionMenu(copyMenus, addList, menuDto, menu.getId());
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
