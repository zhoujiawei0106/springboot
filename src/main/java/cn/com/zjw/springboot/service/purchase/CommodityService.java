package cn.com.zjw.springboot.service.purchase;

import cn.com.zjw.springboot.entity.purchase.Commodity;
import com.github.pagehelper.PageInfo;

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





}
