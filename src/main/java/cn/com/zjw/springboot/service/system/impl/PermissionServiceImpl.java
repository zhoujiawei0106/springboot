package cn.com.zjw.springboot.service.system.impl;

import cn.com.zjw.springboot.service.system.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {
}