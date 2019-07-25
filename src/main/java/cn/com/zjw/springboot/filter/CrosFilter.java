package cn.com.zjw.springboot.filter;

import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;

/**
 * 跨域过滤器
 * @author zhoujiawei
 */
@WebFilter(filterName = "crosFilter", urlPatterns = "/*")
@Order(2)
public class CrosFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(CrosFilter.class);

    public CrosFilter() {
        logger.info("==== 初始化系统允许跨域请求 ====");
    }

    /**
     * 解决跨域：Access-Control-Allow-Origin，值为*表示服务器端允许任意Domain访问请求
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with, sid, mycustom, smuser, token");
            response.addHeader("Access-Control-Max-Age", "1800");//30 min
            return;
        }

        filterChain.doFilter(request, response);
    }
}
