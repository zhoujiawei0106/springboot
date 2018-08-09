package cn.com.zjw.springboot.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtlis {

    private static final Logger logger = LogManager.getLogger(DateUtlis.class);

    /**
     * 默认yyyy-MM-dd HH:mm:ss格式的时间字符串
     * @return 当前系统时间字符串
     * @throws Exception
     */
    public static final String systemTime() throws Exception {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * 根据传入的格式返回时间字符串
     * @param pattern
     * @return 当前系统时间字符串
     * @throws Exception
     */
    public static final String systemTime(String pattern) throws Exception {
        try {
            return new SimpleDateFormat(pattern).format(new Date());
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }
}
