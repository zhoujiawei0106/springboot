package cn.com.zjw.springboot.service.system;

import cn.com.zjw.springboot.dto.system.PermissionDto;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    /**
     * 获取用户可分配及已分配的角色
     * @author zhoujiawei
     * @param userId
     * @param loginUser
     * @return
     * @throws Exception
     */
    public Map<String, List<PermissionDto>> getUserPermission(String userId, String loginUser) throws Exception;
}
