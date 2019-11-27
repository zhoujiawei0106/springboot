package cn.com.zjw.springboot.service.purchase.impl;

import cn.com.zjw.springboot.constants.enumConstants.OrderStatus;
import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.entity.purchase.Order;
import cn.com.zjw.springboot.mapper.purchase.OrderMapper;
import cn.com.zjw.springboot.service.purchase.OrderService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 客户管理
 * @author zhoujiawei
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageInfo getOrders(Order order, String userId) {
        PageHelper.startPage(order.getPage(), order.getRows());
        logger.info("根据条件查询所有订单----" + order.toString());
        List<Order> list = orderMapper.getOrders(order, userId);
        transfer(list);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Order> getOrder(String id, String userId) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("订单编号不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            logger.error("系统异常，上级用户代码为空");
            throw new Exception("系统异常，上级用户代码为空");
        }

        List<Order> orderList = orderMapper.getOrder(id, userId);
        if (orderList == null) {
            throw new Exception("无法获取订单信息");
        }
        logger.info(orderList.toString());

        return orderList;
    }

    @Override
    public Order getCustomerForOrder(String id, String userId) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("订单编码不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            logger.error("系统异常，上级用户代码为空");
            throw new Exception("系统异常，上级用户代码为空");
        }
        Order order = orderMapper.getCustomerForOrder(id, userId);
        if (order == null) {
            throw new Exception("无法获取订单信息");
        }
        logger.info(order.toString());
        return order;
    }

    @Override
    public List<Commodity> getCommodityForOrder(String id, String userId) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("订单编码不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            logger.error("系统异常，上级用户代码为空");
            throw new Exception("系统异常，上级用户代码为空");
        }
        List<Commodity> commodityList = orderMapper.getCommodityForOrder(id);
        return commodityList;
    }

    @Override
    public void save(Order order, String userId) throws Exception {
        logger.info("新增订单信息-----" + order.toString());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYMMddHHmmss");
        String orderId = "OD" + sdf.format(date);
        Order newOrder = new Order();
        newOrder.setId(orderId);
        if(order.getOrderStatus().equals(OrderStatus.Order_FALSE.getValue()) && StringUtils.isNotBlank(order.getTrackId())){
            throw new Exception("未支付状态无法填写快递单号");
        }
        newOrder.setOrderStatus(order.getOrderStatus());
        newOrder.setTrackId(order.getTrackId());
        JSONArray orderArray = JSON.parseArray(order.getTableData());
        orderMapper.saveOrderAndCommodity(orderArray,orderId);
        orderMapper.saveOrder(newOrder,order.getId());
        logger.info("订单信息新增成功");
    }

    @Override
    public void update(Order order, String userId) throws Exception {
        if (StringUtils.isBlank(order.getId())) {
            throw new Exception("订单id不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            logger.error("系统异常，上级用户代码为空");
            throw new Exception("系统异常，上级用户代码为空");
        }
        logger.info("修改订单信息-----" + order.toString());
        Order newOrder = new Order();
        newOrder.setId(order.getId());
        if(order.getOrderStatus().equals(OrderStatus.Order_FALSE.getValue()) && StringUtils.isNotBlank(order.getTrackId())){
            throw new Exception("未支付状态无法填写快递单号");
        }
        newOrder.setOrderStatus(order.getOrderStatus());
        newOrder.setTrackId(order.getTrackId());
        JSONArray orderArray = JSON.parseArray(order.getTableData());
        orderMapper.deleteOrderAndCommodity(order.getId());
        orderMapper.deleteOrder(order.getId());
        orderMapper.saveOrderAndCommodity(orderArray,order.getId());
        orderMapper.saveOrder(newOrder,order.getCustomerId());
        logger.info("订单信息修改成功");
    }

    /**
     * 翻译
     * @author zhoujiawei
     * @param list
     */
    private final void transfer(List<Order> list) {
        for (Order order : list) {
            order.setOrderStatus(OrderStatus.getLabel(order.getOrderStatus()));
        }
    }

}
