package cn.com.zjw.springboot.controller.system;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.dto.system.PermissionDto;
import cn.com.zjw.springboot.service.system.PermissionService;
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
 * 用户权限
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/system/permission")
public class PermissionController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/getUserPermission")
    public Map<String, Object> getUserPermission(String userId, HttpServletRequest request) {
        try {
            Map<String, List<PermissionDto>> map = permissionService.getUserPermission(userId, getUserId(getToken(request)));
            return success(map);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }
}
