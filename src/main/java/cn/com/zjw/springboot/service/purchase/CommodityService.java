package cn.com.zjw.springboot.service.purchase;

import cn.com.zjw.springboot.entity.purchase.Commodity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 客户管理
 * @author zhoujiawei
 */
public interface CommodityService {

    /**
     * 获取客户列表
     * @author zhoujiawei
     * @param commodity
     * @param userId
     * @return
     */
    public PageInfo getCommoditys(Commodity commodity, String userId);

    /**
     * 新增商品
     * @param commodity
     */
    public void save(Commodity commodity);

    /**
     * 修改商品信息
     * @param commodity
     * @param userId
     */
    public void update(Commodity commodity, String userId) throws Exception;

    /**
     * 获取制定商品信息
     * @param id
     * @param userId
     * @return
     */
    public Commodity getCommodity(String id, String userId) throws Exception;

    /**
     * 删除商品
     * @param id
     * @param userId
     */
    public void delete(String id, String userId) throws Exception;

    /**
     * 导出报表
     * @param commodity
     * @param userId
     * @return
     */
    public List<Commodity> export(Commodity commodity, String userId);
}
