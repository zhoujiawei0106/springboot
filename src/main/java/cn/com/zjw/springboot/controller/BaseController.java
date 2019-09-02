package cn.com.zjw.springboot.controller;

import cn.com.zjw.springboot.entity.system.User;
import com.auth0.jwt.JWT;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    /**
     * 获取session中的用户对象
     * @author zhoujiawei
     * @param token
     * @return
     */
    protected String getUserId(String token) throws Exception {
        String id = JWT.decode(token).getClaim("id").asString();
        if (StringUtils.isBlank(id)) {
            throw new Exception("系统异常，未能获取用户信息");
        }
        return id;
    }

    protected String getToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new Exception("系统异常，无法正常获取token");
        }
        return token;
    }

    /**
     * 获取请求ip
     * @author zhoujiawei
     * @param request
     * @return
     */
    protected String getUserIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 请求成功返回前台数据
     * @param msg
     * @return
     */
    public Map<String, Object> success(String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("msg", msg);
        return map;
    }

    /**
     * 请求成功返回前台数据
     * @param data
     * @return
     */
    public Map<String, Object> success(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("data", data);
        return map;
    }

    /**
     * 请求成功返回前台数据
     * @param data
     * @param msg
     * @return
     */
    public Map<String, Object> success(Object data, String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", true);
        map.put("data", data);
        map.put("msg", msg);
        return map;
    }

    /**
     * 请求失败返回前台数据
     * @param msg
     * @return
     */
    public Map<String, Object> fail(String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", false);
        map.put("msg", msg);
        return map;
    }
}
