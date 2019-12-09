package cn.com.zjw.springboot.controller.purchase;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.service.purchase.CustomerService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
     * @param customer
     * @param request
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> list(Customer customer, HttpServletRequest request) {
        try {
            PageInfo pageInfo = customerService.getCustomers(customer, getUserId(getToken(request)));
            return success(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 获取客户列表(order)
     * @author zhoujiawei
     * @param customer
     * @param request
     * @return
     */
    @GetMapping("/listOfOrder")
    public Map<String, Object> listOfOrder(Customer customer, HttpServletRequest request) {
        try {
            List<Customer> list = customerService.getCustomersOfOrder(customer, getUserId(getToken(request)));
            return success(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 保存客户信息
     * @author zhoujiawei
     * @param customer
     * @param request
     * @return
     */
    @PostMapping("/save")
    public Map<String, Object> save(Customer customer, HttpServletRequest request) {
        try {
            customerService.save(customer, getUserId(getToken(request)));
            return success("客户新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 更新客户信息
     * @author zhoujiawei
     * @param customer
     * @param request
     * @return
     */
    @PutMapping("/update")
    public Map<String, Object> update(Customer customer, HttpServletRequest request) {
        try {
            customerService.update(customer, getUserId(getToken(request)));
            return success("客户修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 删除客户信息
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Map<String, Object> delete(String id, HttpServletRequest request) {
        try {
            customerService.delete(id, getUserId(getToken(request)));
            return success("客户删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 加载客户信息
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/getCustomer")
    public Map<String, Object> getCustomer(String id, HttpServletRequest request) {
        try {
            Customer customer = customerService.getCustomer(id, getUserId(getToken(request)));
            return success(customer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 导出客户
     * @author zhoujiawei
     * @param customer
     * @param request
     * @return
     */
    @GetMapping("/export")
    public Map<String, Object> export(Customer customer, HttpServletRequest request) {
        try {
            List<Customer> list = customerService.export(customer, getUserId(getToken(request)));
            return success(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }
}
