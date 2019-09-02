package cn.com.zjw.springboot.mapper.purchase;

import cn.com.zjw.springboot.entity.purchase.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {

    /**
     * 获取客户列表
     * @author zhoujiawei
     * @param customer
     * @param userId
     * @return
     */
    public List<Customer> getCustomers(@Param("customer") Customer customer, @Param("userId") String userId);

    /**
     * 保存客户信息
     * @author zhoujiawei
     * @param customer
     */
    public void save(@Param("customer") Customer customer);

    /**
     * 获取客户信息
     * @author zhoujiawei
     * @param id
     * @param userId
     * @return
     */
    public Customer getCustomer(@Param("id") String id, @Param("userId") String userId);
}
