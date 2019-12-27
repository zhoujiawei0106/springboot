package cn.com.zjw.springboot.controller.system;

import cn.com.zjw.springboot.constants.enumConstants.UserType;
import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.dto.system.PermissionDto;
import cn.com.zjw.springboot.entity.system.User;
import cn.com.zjw.springboot.service.system.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 获取用户
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/getUsers")
    public Map<String, Object> getUsers(User user) {
        try {
            PageInfo pageInfo = userService.getUsers(user);
            return success(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 新增用户
     * @author zhoujiawei
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/save")
    public Map<String, Object> save(User user, HttpServletRequest request) {
        try {
            // 用户管理只能新增管理员用户
            user.setUserType(Integer.valueOf(UserType.Admin.getValue()));
            user.setIp(getUserIp(request));
            userService.save(user);
            return success("用户新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 获取用户信息
     * @author zhoujiawei
     * @param user
     * @return
     */
    @GetMapping("/getUser")
    public Map<String, Object> getUser(User user) {
        try {
            user = userService.getUser(user);
            return success(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 更新用户信息
     * @author zhoujiawei
     * @param user
     * @param oldPwd
     * @return
     */
    @PutMapping("/update")
    public Map<String, Object> update(User user, String oldPwd) {
        try {
            userService.update(user, oldPwd);
            return success("用户修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 删除用户
     * @author zhoujiawei
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Map<String, Object> delete(String id) {
        try {
            userService.delete(id);
            return success("用户删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 重置用户密码,密码为用户登录名
     * @author zhoujiawei
     * @param id
     * @return
     */
    @PutMapping("/resetPwd")
    public Map<String, Object> resetPwd(String id) {
        try {
            userService.resetPwd(id);
            return success("重置用户登陆次数成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 重置用户登陆次数
     * @author zhoujiawei
     * @param id
     * @return
     */
    @PutMapping("/resetTimes")
    public Map<String, Object> resetTimes(String id) {
        try {
            userService.resetTimes(id);
            return success("重置用户登陆次数成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }
}
