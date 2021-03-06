package cn.com.zjw.springboot.mapper.purchase;

import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.entity.system.User;
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

    /**
     * 更新客户信息
     * @author zhoujiawei
     * @param customer
     */
    public void update(@Param("customer") Customer customer);

    /**
     * 删除客户信息
     * @author zhoujiawei
     * @param id
     */
    public void delete(@Param("id") String id);

    /**
     * 查询客户信息
     * @param userId
     */
    public Customer getCustomerd(String userId);

    /**
     * 根据登陆用户获取客户信息
     * @author zhoujiawei
     * @param userId
     * @return
     */
    public Customer getByLoginUser(String userId);

    /**
     * 获取代购客户的用户信息
     * @author zhoujiawei
     * @param userId
     * @param customerType
     * @param userStatus
     * @return
     */
    public User getPurchaserCustomer(@Param("userId") String userId, @Param("customerType") String customerType,
                                     @Param("userStatus") String userStatus);
}
