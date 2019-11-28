package cn.com.zjw.springboot.controller.purchase;

import cn.com.zjw.springboot.constants.enumConstants.OrderStatus;
import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.entity.purchase.Order;
import cn.com.zjw.springboot.service.purchase.CustomerService;
import cn.com.zjw.springboot.service.purchase.OrderService;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单管理
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/purchase/order")
public class OrderController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;


    /**
     * 获取订单列表
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> list(Order order, HttpServletRequest request) {
        try {
            PageInfo pageInfo = orderService.getOrders(order, getUserId(getToken(request)));
            return success(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 加载订单信息
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/getOrder")
    public Map<String, Object> getOrder(String id, HttpServletRequest request) {
        try {
            Order order = orderService.getCustomerForOrder(id,getUserId(getToken(request)));
            List<Commodity> commodityList = orderService.getCommodityForOrder(id,getUserId(getToken(request)));
            return success(order,commodityList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 新增订单信息
     * @author zhoujiawei
     * @param request
     * @return
     */
    @PostMapping(value = "/save")
    public Map<String, Object> save(Order order, HttpServletRequest request) {
        try {
            orderService.save(order, getUserId(getToken(request)));
            return success("订单新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 更新订单信息
     * @author zhoujiawei
     * @param order
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Map<String, Object> update(Order order, HttpServletRequest request) {
        try {
            orderService.update(order, getUserId(getToken(request)));
            return success("订单修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 获取订单状态常量
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/orderStatus")
    public Map<String, Object> orderStatus() {
        try {
            return success(OrderStatus.getOrderStatus());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 导出订单
     * @author zhoujiawei
     * @param order
     * @param request
     * @return
     */
    @GetMapping("/export")
    public Map<String, Object> export(Order order, HttpServletRequest request) {
        try {
            List<Order> list = orderService.export(order, getUserId(getToken(request)));
            return success(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

}
