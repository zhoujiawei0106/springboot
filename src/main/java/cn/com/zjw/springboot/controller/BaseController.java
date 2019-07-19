package cn.com.zjw.springboot.controller;

import cn.com.zjw.springboot.constants.CodeConstants;
import cn.com.zjw.springboot.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    /**
     * 获取session中的用户对象
     * @param request
     * @return
     */
    protected User getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        return (User) session.getAttribute(CodeConstants.SESSION_LOGIN_USER);
    }

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
