package cn.com.zjw.springboot.filter;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.User;
import cn.com.zjw.springboot.service.UserService;
import cn.com.zjw.springboot.utils.JsonParseUtils;
import cn.com.zjw.springboot.utils.SpringContextUtils;
import cn.com.zjw.springboot.utils.TokenUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登陆过滤器
 * @author zhoujiawei
 */
@WebFilter(filterName = "userCheckFilter", urlPatterns = "/*")
@Order(3)
public class UserCheckFilter implements Filter {

    private Logger logger = LogManager.getLogger(UserCheckFilter.class);

    private static final String url = "/login/;";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("初始化用户登陆过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String[] urls = url.split(";");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 当前访问的路径
        String url = request.getRequestURI();
        logger.warn("=======================================当前执行的请求路径为:" + url);

        for (String str : urls) {
            // 如果直接访问登陆页面，或访问配置文件中配置的不过滤url不做处理
            if (url.indexOf(str) > 0) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }

        logger.info("开始执行用户登陆过滤器");

        // 获取头中的token
        String token = request.getHeader("token");
        logger.warn(token + "========================");

        // 执行认证
        if (StringUtils.isBlank(token)) {
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            Map<String, Object> map = failMap("无token，请重新登录");
            response.getWriter().write(JsonParseUtils.toJson(map));
            response.addHeader("Access-Control-Allow-Origin", "*");
            return;
        }
//        // 获取 token 中的 user id
//        String userId;
//        try {
//            userId = JWT.decode(token).getClaim("id").asString();
//        } catch (JWTDecodeException j) {
//            throw new RuntimeException("访问异常！");
//        }
//        User user = SpringContextUtils.getBean(UserService.class).getUser(userId);
//        if (user == null) {
//            throw new RuntimeException("用户不存在，请重新登录");
//        }
//        Boolean verify = TokenUtils.isVerify(token, user);
//        if (!verify) {
//            throw new RuntimeException("非法访问！");
//        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("销毁用户登陆过滤器");
    }

    /**
     * 验证失败的返回值
     *
     * @param msg
     * @return
     * @author zhoujiawei
     */
    private static final Map<String, Object> failMap(String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", msg);
        map.put("flag", false);
        map.put("code", "412");
        return map;
    }
}