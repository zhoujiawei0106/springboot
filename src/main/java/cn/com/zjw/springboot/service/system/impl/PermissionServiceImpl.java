package cn.com.zjw.springboot.service.system.impl;

import cn.com.zjw.springboot.dto.system.PermissionDto;
import cn.com.zjw.springboot.mapper.system.PermissionMapper;
import cn.com.zjw.springboot.mapper.system.UserMapper;
import cn.com.zjw.springboot.service.system.PermissionService;
import cn.com.zjw.springboot.utils.PinyinUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    private Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;

    /*@Override
    public Map<String, List<PermissionDto>> getUserPermission(String userId, String loginUser) throws Exception {
        Map<String, List<PermissionDto>> map = new HashMap<>();
        List<PermissionDto> dataList = permissionMapper.getUndistributedRoles(userId, loginUser);
        *//*for (PermissionDto permissionDto : dataList) {
            permissionDto.setPinyin(PinyinUtils.toPinyin(permissionDto.getLabel()));
        }
*//*
        map.put("undistributed", dataList);
        map.put("distribute", dataList);
        return map;
    }*/

    @Override
    public void saveUserPermission(String userId, String role, String userSessionId) {
        //判断用户权限是否存在
        Integer count = permissionMapper.getUserPermission(userId);
        //根据角色id找出对应的权限id
        JSONArray roleArray = JSON.parseArray(role);
        List<PermissionDto> permissionId = permissionMapper.getPermissionByRoleId(roleArray);
        if(count <= 0) {
            //不存在、新增
            permissionMapper.saveUserPermission(userId, permissionId);
        } else {
            //存在更新
            permissionMapper.deleteUserPermission(userId);
            permissionMapper.saveUserPermission(userId, permissionId);
        }

    }
}
