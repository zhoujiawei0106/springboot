package cn.com.zjw.springboot.service.system.impl;

import cn.com.zjw.springboot.constants.enumConstants.CustomerStatus;
import cn.com.zjw.springboot.entity.system.Role;
import cn.com.zjw.springboot.entity.system.User;
import cn.com.zjw.springboot.mapper.system.RoleMapper;
import cn.com.zjw.springboot.mapper.system.UserMapper;
import cn.com.zjw.springboot.service.system.RoleService;
import cn.com.zjw.springboot.utils.CommonUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo getRoles(Role role) {
        PageHelper.startPage(role.getPage(), role.getRows());
        logger.info("根据条件查询所有用户----" + role.toString());
        List<Role> list = roleMapper.getRoles(role);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void save(Role role, String menus, String userId) throws Exception{
        // 将json字符串转换成对象
        List<JSONObject> list = JSON.parseObject(menus, List.class);

        check(role, list, userId);

        // 分别插入角色，权限，角色权限表
        roleMapper.insert(role, userId);
        logger.info("插入角色信息成功" + role.toString());

        String permissionId = CommonUtils.getUUID();
        roleMapper.insertPermission(permissionId);
        logger.info("插入权限信息成功");

        if (list != null && list.size() > 0) {
            insertRolePermission(list, role.getId(), permissionId);
        }
        logger.info("插入角色权限信息成功");
    }

    @Override
    public Role getByRoleIdAndUserId(String roleId, String userId) throws Exception {
        if (StringUtils.isBlank(roleId)) {
            throw new Exception("角色代码不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            throw new Exception("用户代码不能为空");
        }
        return roleMapper.getByRoleIdAndUserId(roleId, userId);
    }

    private final void check(Role role, List<JSONObject> list, String userId) throws Exception {
        if (role == null) {
            throw new Exception("角色信息为空");
        }
        if (StringUtils.isBlank(role.getId())) {
            role.setId(CommonUtils.getUUID());
        }
        if (StringUtils.isBlank(role.getRoleName())) {
            throw new Exception("角色名称不能为空");
        }
        for (JSONObject jsonObject : list) {
            if (jsonObject != null && jsonObject.get("children") == null &&
                    ((JSONArray)jsonObject.get("children")).size() > 0) {
                throw new Exception("分配的菜单不能只存在一级菜单");
            }
        }

        User user = userMapper.getUserById(userId);
        if (user == null || user.getStatus().equals(CustomerStatus.Invalid)) {
            throw new Exception("登陆用户不存在或状态非有效");
        }

        role.setUserId(userId);
        if (roleMapper.getRoles(role).size() > 15) {
            throw new Exception("一个用户最多只能添加15个角色");
        }
    }

    /**
     * 递归插入角色权限信息
     * @author zhoujiawei
     * @param list
     * @param roleId
     * @param permissionId
     */
    private final void insertRolePermission(List<JSONObject> list, String roleId, String permissionId) {
        roleMapper.insertRolePermission(list, roleId, permissionId);
        for (JSONObject jsonObject : list) {
            if (jsonObject.get("children") != null && ((JSONArray)jsonObject.get("children")).size() > 0) {
                insertRolePermission(((List<JSONObject>)jsonObject.get("children")), roleId, permissionId);
            }
        }
    }
}
