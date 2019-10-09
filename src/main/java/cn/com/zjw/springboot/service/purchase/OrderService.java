package cn.com.zjw.springboot.service.purchase;

import cn.com.zjw.springboot.entity.purchase.Order;
import com.github.pagehelper.PageInfo;

/**
 * 订单管理
 * @author zhoujiawei
 */
public interface OrderService {

    /**
     * 获取订单列表
     * @author zhoujiawei
     * @param order
     * @param userId
     * @return
     */
    public PageInfo getOrders(Order order, String userId);

    /**
     * 获取订单信息
     * @param id
     * @param userId
     * @return
     */
    public Order getOrder(String id, String userId) throws Exception;

    /**
     * 修改订单信息
     * @param order
     * @param userId
     */
    public void update(Order order, String userId) throws Exception;
}
