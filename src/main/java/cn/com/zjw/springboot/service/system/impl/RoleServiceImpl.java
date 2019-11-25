package cn.com.zjw.springboot.service.system.impl;

import cn.com.zjw.springboot.entity.system.Role;
import cn.com.zjw.springboot.mapper.system.RoleMapper;
import cn.com.zjw.springboot.service.system.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo getRoles(Role role) {
        PageHelper.startPage(role.getPage(), role.getRows());
        logger.info("根据条件查询所有用户----" + role.toString());
        List<Role> list = roleMapper.getRoles(role);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
