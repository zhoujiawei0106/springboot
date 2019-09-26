package cn.com.zjw.springboot.mapper.purchase;

import cn.com.zjw.springboot.entity.purchase.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapper {

    /**
     * 获取客户列表
     * @author zhoujiawei
     * @param commodity
     * @param userId
     * @return
     */
    public List<Commodity> getCommoditys(@Param("Commodity") Commodity commodity, @Param("userId") String userId);

}
