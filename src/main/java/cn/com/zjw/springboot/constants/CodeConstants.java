package cn.com.zjw.springboot.constants;

import java.util.Map;

public class CodeConstants {

    public static final String SESSION_LOGIN_USER = "LOGIN_USER";

    /**
     * system_param表中的code_type
     */
    public static final String CODE_TYPE_LANGUAGE = "language";

    /**
     * 语言code
     */
    // 简体中文
    public static final int LANGUAGE_CN_CODE = 1;
    // 英语
    public static final int LANGUAGE_EN_CODE = 2;

    /**
     * 语言type
     */
    // 简体中文
    public static final String LANGUAGE_CN_TYPE = "zh";
    // 英语
    public static final String LANGUAGE_EN_TYPE = "en";

    /**
     * token失效时间(1小时)
     */
    public static final long EXPIRE_TIME = 3600000;

    /**
     * 错误码
     */
    // 登陆失效
    public static final int LOGIN_INVALID = 400001;
    // 没有操作权限
    public static final int PERMISSION_FAIL = 400002;

    /**
     * 注销状态
     */
    // 有效
    public static final String VAILD = "1";
    // 注销
    public static final String CANCEL = "2";
}
