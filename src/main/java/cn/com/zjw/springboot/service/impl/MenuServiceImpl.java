package cn.com.zjw.springboot.service.impl;

import cn.com.zjw.springboot.entity.Menu;
import cn.com.zjw.springboot.mapper.MenuMapper;
import cn.com.zjw.springboot.service.MenuService;
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
