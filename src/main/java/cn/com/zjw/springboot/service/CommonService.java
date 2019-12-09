package cn.com.zjw.springboot.service;

import java.util.List;
import java.util.Map;

public interface CommonService {

    /**
     * 根据登陆用户获取
     * @author zhoujiawei
     * @param userId
     * @return
     */
    public List<Map<String, String>> customerType(String userId) throws Exception;
}
