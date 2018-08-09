package cn.com.zjw.springboot.aspect;

import cn.com.zjw.springboot.utils.DateUtlis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 记录进入和执行完controller层的时间
 * @author zhoujiawei
 */
@Aspect
@Component
public class LogControllerAspect {

    private static final Logger logger = LogManager.getLogger(LogControllerAspect.class);

    @Pointcut("execution(public * cn.com.zjw.springboot.controller.*.*(..))")
    public void logControllerAspect() {}

    /**
     * 目标方法执行之前调用
     * @param joinPoint
     * @throws Exception
     */
    @Before("logControllerAspect()")
    public void before(JoinPoint joinPoint) throws Exception {
        String className = getClassName(joinPoint);
        String method = getMethodName(joinPoint);
        logger.info(DateUtlis.systemTime() + "开始执行" + className + "的" + method + "方法");
    }

    /**
     * 目标方法返回或者抛出异常之后调用
     * @param joinPoint
     * @throws Exception
     */
    @After("logControllerAspect()")
    public void after(JoinPoint joinPoint) throws Exception {
        String className = getClassName(joinPoint);
        String method = getMethodName(joinPoint);
        logger.info(DateUtlis.systemTime() + "执行完成" + className + "的" + method + "方法");
    }

    /**
     * 获取类名
     * @param joinPoint
     * @return
     */
    private static final String getClassName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringTypeName();
    }

    /**
     * 获取方法名
     * @param joinPoint
     * @return
     */
    private static final String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }
}
