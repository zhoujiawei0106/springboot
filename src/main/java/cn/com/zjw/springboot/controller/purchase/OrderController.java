package cn.com.zjw.springboot.controller.purchase;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Inventory;
import cn.com.zjw.springboot.entity.purchase.Order;
import cn.com.zjw.springboot.service.purchase.InventoryService;
import cn.com.zjw.springboot.service.purchase.OrderService;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Map<String, Object> getInventory(String id, HttpServletRequest request) {
        try {
            Order order = orderService.getOrder(id, getUserId(getToken(request)));
            return success(order);
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
    @PutMapping("/update")
    public Map<String, Object> update(Order order, HttpServletRequest request) {
        try {
            orderService.update(order, getUserId(getToken(request)));
            return success("订单修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

}
