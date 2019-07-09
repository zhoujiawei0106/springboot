package cn.com.zjw.springboot.controller;

import cn.com.zjw.springboot.entity.User;
import cn.com.zjw.springboot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 登陆
 */
@RestController
public class LoginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

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

            // TODO 返回菜单
            return success("登陆成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }


    }
}
