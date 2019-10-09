package cn.com.zjw.springboot.service.purchase.impl;

import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Inventory;
import cn.com.zjw.springboot.entity.purchase.Order;
import cn.com.zjw.springboot.mapper.purchase.InventoryMapper;
import cn.com.zjw.springboot.mapper.purchase.OrderMapper;
import cn.com.zjw.springboot.service.purchase.InventoryService;
import cn.com.zjw.springboot.service.purchase.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public Order getOrder(String id, String userId) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("订单编号不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            logger.error("系统异常，上级用户代码为空");
            throw new Exception("系统异常，上级用户代码为空");
        }

        Order order = orderMapper.getOrder(id, userId);
        if (order == null) {
            throw new Exception("无法获取订单信息");
        }
        logger.info(order.toString());

        return order;
    }

    @Override
    public void update(Order order, String userId) throws Exception {
        if (StringUtils.isBlank(order.getId())) {
            throw new Exception("请选择一条记录");
        }
        logger.info("修改订单信息-----" + order.toString());
        orderMapper.update(order);
        logger.info("订单信息修改成功");
    }


}
