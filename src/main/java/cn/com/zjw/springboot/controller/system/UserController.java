package cn.com.zjw.springboot.controller.system;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.system.User;
import cn.com.zjw.springboot.service.system.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * @return
     */
    @PutMapping("/update")
    public Map<String, Object> update(User user) {
        try {
            userService.update(user);
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
     * 重置用户登陆次数
     * @author zhoujiawei
     * @param id
     * @return
     */
    @PutMapping("/reset")
    public Map<String, Object> reset(String id) {
        try {
            userService.reset(id);
            return success("重置用户登陆次数成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

}
