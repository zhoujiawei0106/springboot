package cn.com.zjw.springboot.mapper.purchase;

import cn.com.zjw.springboot.entity.purchase.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {



    /**
     * 获取库存商品列表
     * @author zhoujiawei
     * @param order
     * @param userId
     * @return
     */
    public List<Order> getOrders(@Param("order") Order order, @Param("userId") String userId);

    /**
     * 获取订单信息
     * @param id
     * @param userId
     * @return
     */
    public List<Order> getOrder(@Param("id")String id, String userId);

    /**
     * 修改订单信息
     * @param order
     */
    public void update(@Param("order") Order order,@Param("id") String id,@Param("orderList") List<Order> orderList);

    /**
     * 新增订单信息
     * @param orderList
     */
    public void save(@Param("order") Order order,@Param("orderList") List<Order> orderList);

    /**
     * 删除订单信息
     * @param id
     */
    public void delete(String id);
}
