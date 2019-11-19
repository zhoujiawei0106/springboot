package cn.com.zjw.springboot.controller.system;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.service.system.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户权限
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;
}
