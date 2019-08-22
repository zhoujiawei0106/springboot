package cn.com.zjw.springboot.mapper.system;

import cn.com.zjw.springboot.entity.system.Menu;
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
