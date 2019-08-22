package cn.com.zjw.springboot.service.system.impl;

import cn.com.zjw.springboot.entity.system.Menu;
import cn.com.zjw.springboot.mapper.system.MenuMapper;
import cn.com.zjw.springboot.service.system.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getUserMenu(String userId) {
        return menuMapper.getUserMenu(userId);
    }
}
