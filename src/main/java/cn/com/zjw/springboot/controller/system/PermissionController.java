package cn.com.zjw.springboot.controller.system;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.dto.system.PermissionDto;
import cn.com.zjw.springboot.dto.system.PermissionMenu;
import cn.com.zjw.springboot.entity.system.Role;
import cn.com.zjw.springboot.service.system.MenuService;
import cn.com.zjw.springboot.service.system.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户权限
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/system/permission")
public class PermissionController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private MenuService menuService;


    @GetMapping("/getUserPermission")
    public Map<String, Object> getUserPermission(String userId, HttpServletRequest request) {
        try {
            /*Map<String, List<PermissionDto>> map = permissionService.getUserPermission(userId, getUserId(getToken(request)));*/
            Map<String, List<PermissionMenu>> map = menuService.getData(userId, getUserId(getToken(request)));
            return success(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 权限分配
     * @author zhoujiawei
     * @param userId   被分配的用户
     * @param request 当前的用户
     * @return
     */
    @PutMapping("/save")
    public Map<String, Object> saveUserPermission(String userId, String role,HttpServletRequest request) {
        try {
            permissionService.saveUserPermission(userId, role, getUserId(getToken(request)));
            return success("权限分配成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }
}
