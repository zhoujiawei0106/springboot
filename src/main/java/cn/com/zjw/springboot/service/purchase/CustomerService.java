package cn.com.zjw.springboot.service.purchase;

import cn.com.zjw.springboot.entity.purchase.Customer;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * 客户管理
 * @author zhoujiawei
 */
public interface CustomerService {

    /**
     * 获取客户列表
     * @author zhoujiawei
     * @param customer
     * @param userId
     * @return
     */
    public PageInfo getCustomers(Customer customer, String userId);

    /**
     * 获取客户列表(order)
     * @param customer
     * @param userId
     * @return
     */
    List<Customer> getCustomersOfOrder(Customer customer, String userId);

    /**
     * 保存客户信息
     * @author zhoujiawei
     * @param customer
     * @param userId 登陆用户的id
     * @param expiringDate
     * @throws Exception
     */
    public void save(Customer customer, String userId, Date expiringDate) throws Exception;

    /**
     * 获取客户信息
     * @author zhoujiawe
     * @param id
     * @param userId
     * @return
     */
    public Customer getCustomer(String id, String userId) throws Exception;

    /**
     * 修改客户信息
     * @author zhoujiawei
     * @param customer
     * @param userId
     * @param expiringDate
     * @throws Exception
     */
    public void update(Customer customer, String userId, Date expiringDate) throws Exception;

    /**
     * 删除客户信息
     * @author zhoujiawei
     * @param id
     * @param userId
     * @throws Exception
     */
    public void delete(String id, String userId) throws Exception;

    /**
     * 获取导出的数据
     * @author zhoujiawei
     * @param customer
     * @param userId
     * @return
     */
    public List<Customer> export(Customer customer, String userId);

    /**
     * 获取登陆用户的用户类型
     * @author zhoujiawei
     * @param userId
     * @return true管理员 false普通用户
     */
    public Object getUserType(String userId);
}
