package cn.com.zjw.springboot.service.system.impl;

import cn.com.zjw.springboot.dto.system.PermissionDto;
import cn.com.zjw.springboot.mapper.system.PermissionMapper;
import cn.com.zjw.springboot.service.system.PermissionService;
import cn.com.zjw.springboot.utils.PinyinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    private Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Map<String, List<PermissionDto>> getUserPermission(String userId, String loginUser) throws Exception {
        Map<String, List<PermissionDto>> map = new HashMap<>();

        List<PermissionDto> dataList = permissionMapper.getUndistributedRoles(userId, loginUser);
        for (PermissionDto permissionDto : dataList) {
            permissionDto.setPinyin(PinyinUtils.toPinyin(permissionDto.getLabel()));
        }

        map.put("data", dataList);

        return map;
    }
}
