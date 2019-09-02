package cn.com.zjw.springboot.service.purchase;

import cn.com.zjw.springboot.entity.purchase.Customer;
import com.github.pagehelper.PageInfo;

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
     * 保存客户信息
     * @author zhoujiawei
     * @param customer
     * @throws Exception
     */
    public void save(Customer customer) throws Exception;
}
