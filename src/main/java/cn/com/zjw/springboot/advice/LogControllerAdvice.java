package cn.com.zjw.springboot.advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 拦截所有controller中的异常，并打印到控制台和日志文件中
 */
@ControllerAdvice
public class LogControllerAdvice {

    private static final Logger logger = LogManager.getLogger(LogControllerAdvice.class);

    /**
     * 使用注解的形式拦截controller层的异常
     * 可配置ExceptionHandler(value = {Exception.class})或其他异常
     * 拦截异常后可返回信息(Map,result等方式配合@ResponseBody注解)，也可void不对页面做任何处理
     * @param e
     */
    @ExceptionHandler
    public void loging(Exception e) {
        logger.error(e.getMessage(), e);
    }
}
