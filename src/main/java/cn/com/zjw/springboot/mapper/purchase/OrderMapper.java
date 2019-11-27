package cn.com.zjw.springboot.mapper.purchase;

import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.entity.purchase.Order;
import com.alibaba.fastjson.JSONArray;
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
     * 新增订单信息
     * @param orderList
     */
    public void save(@Param("order") Order order,@Param("orderList") List<Order> orderList);

    /**
     * 保存订单和商品信息
     * @param orderArray
     */
    public void saveOrderAndCommodity(@Param("orderArray") JSONArray orderArray, @Param("orderId") String orderId);

    /**
     * 保存订单信息
     * @param order
     */
    public void saveOrder(@Param("order")Order order, @Param("customerId")String customerId);

    /**
     * 获取订单客户信息
     * @param id
     * @param userId
     * @return
     */
    public Order getCustomerForOrder(@Param("id")String id, String userId);

    /**
     * 获取订单客户商品信息
     * @param id
     * @return
     */
    public List<Commodity> getCommodityForOrder(String id);

    /**
     * 删除订单信息
     * @param id
     */
    public void deleteOrder(String id);

    /**
     * 删除关联表信息
     * @param id
     */
    public void deleteOrderAndCommodity(String id);
}
