package cn.com.zjw.springboot.controller;

import cn.com.zjw.springboot.entity.User;
import cn.com.zjw.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 登陆
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login() throws Exception {
        return success("/test");
    }

    @RequestMapping("/index")
    public String index() throws Exception {
        User user = userService.getUser("admin");
        return "/test";
    }
}
