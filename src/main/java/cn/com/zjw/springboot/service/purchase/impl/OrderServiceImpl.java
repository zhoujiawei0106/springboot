package cn.com.zjw.springboot.service.purchase.impl;

import cn.com.zjw.springboot.constants.enumConstants.CustomerType;
import cn.com.zjw.springboot.constants.enumConstants.OrderStatus;
import cn.com.zjw.springboot.constants.enumConstants.ValidStatus;
import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.entity.purchase.Order;
import cn.com.zjw.springboot.mapper.purchase.CommodityMapper;
import cn.com.zjw.springboot.mapper.purchase.CustomerMapper;
import cn.com.zjw.springboot.mapper.purchase.OrderMapper;
import cn.com.zjw.springboot.service.purchase.OrderService;
import cn.com.zjw.springboot.utils.CommonUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public PageInfo getOrders(Order order, String userId) {
        PageHelper.startPage(order.getPage(), order.getRows());
        logger.info("根据条件查询所有订单----" + order.toString());
        //先暂时userId为空，后面根据类型改
        Customer customer = customerMapper.getCustomerd(userId);
        List<Order> list;
        if(customer == null){
            list = orderMapper.getOrders(order, userId);
        } else if(CustomerType.getLabel(customer.getType()).equals(CustomerType.Agency)){
            list = orderMapper.getOrders(order, null);
        } else {
            list = orderMapper.getOrders(order, userId);
        }
        transfer(list,null);
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
    public Map<String, Object> getCustomerAndCommodityForOrder(String id, String userId) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("订单编码不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            logger.error("系统异常，上级用户代码为空");
            throw new Exception("系统异常，上级用户代码为空");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Order customer = orderMapper.getCustomerForOrder(id, userId);
        if (customer == null) {
            throw new Exception("无法获取订单客户信息");
        }
        List<Commodity> commodityList = orderMapper.getCommodityForOrder(id,null,userId);
        map.put("customer",customer);
        map.put("commodityList",commodityList);
        map.put("flag", true);
        map.put("code", 200000);
        logger.info(customer.toString());
        logger.info(commodityList.toString());
        return map;
    }

    @Override
    public Map<String, Object> getOrderInfo(Customer customer, Commodity commodity, String userId) {
        logger.info("根据条件查询所有用户----" + customer.toString());
        List<Customer> customerList = customerMapper.getCustomers(customer, userId);
        transfer(null,customerList);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        logger.info("根据条件查询所有商品----" + commodity.toString());
        //先暂时userId为空，后面根据类型改、
        commodity.setIsValid(String.valueOf(ValidStatus.Valid.getValue()));
        List<Commodity> commodityList;
        if(customer == null){
            commodityList = commodityMapper.getCommoditys(null, commodity, userId);
        } else if(CustomerType.getLabel(customer.getType()).equals(CustomerType.Agency)){
            commodityList = commodityMapper.getCommoditys(customer, commodity, null);
        } else {
            commodityList = commodityMapper.getCommoditys(customer, commodity, userId);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> dataById = new HashMap<String, Object>();
        Map<String, Object> data = new HashMap<String, Object>();
        for(Commodity c : commodityList) {
            dataById.put(c.getId(),c);
        }
        dataById.put("data",commodityList);
        data.put("data",commodityList);
        map.put("flag", true);
        //customer的数据信息
        map.put("list", customerList);
        //id分散格式(commodity)
        map.put("dataById", dataById);
        //合并模式(commodity)
        map.put("data", data);
        map.put("code", 200000);
        return map;
    }

    @Override
    public void save(Order order, String userId) throws Exception {
        orderCheck(order, userId);
        logger.info("新增订单信息-----" + order.toString());
        /*Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYMMddHHmmss");
        String orderId = "OD" + sdf.format(date);*/
        String orderId = CommonUtils.getUUID();
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
        orderCheck(order,userId);
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

    @Override
    public List<Order> export(Order order, String userId) {
        logger.info("根据条件查询所有订单----" + order.toString());
        List<Order> list = orderMapper.getOrders(order, userId);
        transfer(list,null);
        logger.info("导出的订单数据共 " + list.size() + "条");
        return list;
    }

    /**
     * 订单校验
     * @param order
     * @param userId
     */
    private void orderCheck(Order order, String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            logger.error("系统异常，上级用户代码为空");
            throw new Exception("系统异常上级用户代码为空");
        }
        if(StringUtils.isBlank(order.getTableData())) {
            throw new Exception("订单客户信息不能为空");
        }
        if (StringUtils.isBlank(order.getId())) {
            throw new Exception("订单id不能为空");
        }
    }

    /**
     * 翻译
     * @author zhoujiawei
     * @param orderList
     * @param customerList
     */
    private final void transfer(List<Order> orderList, List<Customer> customerList) {
        if(orderList != null) {
            for (Order order : orderList) {
                order.setOrderStatus(OrderStatus.getLabel(order.getOrderStatus()));
            }
        }
        if(customerList != null) {
            for (Customer customer : customerList) {
                customer.setType(CustomerType.getLabel(customer.getType()));
                customer.setStatus(ValidStatus.getLabel(customer.getStatus()));
            }
        }
    }

}
