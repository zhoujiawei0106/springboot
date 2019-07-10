package cn.com.zjw.springboot.mapper;

import cn.com.zjw.springboot.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {

    /**
     * 根据登陆用户获取菜单
     * @author zhoujiawei
     * @param userId
     * @return
     */
    public List<Menu> getUserMenu(@Param("userId") String userId);
}
