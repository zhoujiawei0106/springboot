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
    public List<Commodity> getCommoditys(@Param("commodity") Commodity commodity, @Param("userId") String userId);

    /**
     * 保存客户信息
     * @author zhoujiawei
     * @param commodity
     */
    public void save(@Param("commodity") Commodity commodity, @Param("userId") String userId);

    /**
     * 查询商品信息
     * @param id
     * @param userId
     * @return
     */
    public Commodity getCommodity(@Param("id") String id, @Param("userId") String userId);

    /**
     * 修改商品信息
     * @param commodity
     */
    public void update(@Param("commodity") Commodity commodity, @Param("userId") String userId);

    /**
     * 删除商品
     * @param id
     */
    public void delete(@Param("id") String id, @Param("userId") String userId);
}
