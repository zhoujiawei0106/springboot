package cn.com.zjw.springboot.controller.purchase;

import cn.com.zjw.springboot.constants.purchase.CustomerStatus;
import cn.com.zjw.springboot.constants.purchase.CustomerType;
import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.service.purchase.CustomerService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 客户管理
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/purchase/customer")
public class CustomerController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * 获取客户列表
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> list(Customer customer) {
        try {
            PageInfo pageInfo = customerService.getCustomers(customer);
            return success(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    @PostMapping("/save")
    public Map<String, Object> save(Customer customer) {
        try {
            customerService.save(customer);
            return success("客户新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 获取客户类型常量
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/customerType")
    public Map<String, Object> customerType() {
        try {
            return success(CustomerType.getCustomerType());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 获取客户状态常量
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/customerStatus")
    public Map<String, Object> customerStatus() {
        try {
            return success(CustomerStatus.getCustomerStatus());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }
}
