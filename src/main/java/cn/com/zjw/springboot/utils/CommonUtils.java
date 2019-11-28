package cn.com.zjw.springboot.utils;

import java.util.UUID;

/**
 * 公用的utils
 * @author zhoujiawei
 */
public class CommonUtils {

    public static final String getUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}
