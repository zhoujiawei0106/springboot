package cn.com.zjw.springboot.controller.system;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.dto.system.PermissionMenu;
import cn.com.zjw.springboot.entity.system.Role;
import cn.com.zjw.springboot.service.system.MenuService;
import cn.com.zjw.springboot.service.system.RoleService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户角色
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 获取角色
     * @author zhoujiawei
     * @param role
     * @param request
     * @return
     */
    @GetMapping("/getRoles")
    public Map<String, Object> getUsers(Role role, HttpServletRequest request) {
        try {
            role.setUserId(getUserId(getToken(request)));
            PageInfo pageInfo = roleService.getRoles(role);
            return success(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 获取登陆用户未分配菜单(所有用户均只能看到自己的角色)
     * @author zhoujiawei
     * @param roleId
     * @param type 操作类型，新增查询还是修改查询
     * @param request
     * @return
     */
    @GetMapping("/getUndistributedMenu")
    public Map<String, Object> getUndistributedMenu(String roleId, String type, HttpServletRequest request) {
        try {
            List<PermissionMenu> list = menuService.getUndistributedMenu(getUserId(getToken(request)), type, roleId);
            return success(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }

    }
}
