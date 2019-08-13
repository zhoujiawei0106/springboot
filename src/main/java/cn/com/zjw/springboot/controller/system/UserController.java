package cn.com.zjw.springboot.controller.system;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.User;
import cn.com.zjw.springboot.service.UserService;
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
     * 回去用户
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

    @PostMapping("save")
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
}
