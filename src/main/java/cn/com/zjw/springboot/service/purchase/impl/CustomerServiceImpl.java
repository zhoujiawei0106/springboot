package cn.com.zjw.springboot.service.purchase.impl;

import cn.com.zjw.springboot.constants.purchase.CustomerStatus;
import cn.com.zjw.springboot.constants.purchase.CustomerType;
import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.entity.system.User;
import cn.com.zjw.springboot.mapper.purchase.CustomerMapper;
import cn.com.zjw.springboot.service.purchase.CustomerService;
import cn.com.zjw.springboot.service.system.UserService;
import cn.com.zjw.springboot.utils.BlowfishCipher;
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
public class CustomerServiceImpl implements CustomerService {

    private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private UserService userService;

    @Override
    public PageInfo getCustomers(Customer customer, String userId) {
        PageHelper.startPage(customer.getPage(), customer.getRows());
        logger.info("根据条件查询所有用户----" + customer.toString());
        List<Customer> list = customerMapper.getCustomers(customer, userId);
        transfer(list);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void save(Customer customer) throws Exception {
        checkData(customer);

        logger.info("新增客户----" + customer.toString());
        customerMapper.save(customer);
        logger.info("客户信息新增成功");

        User user = new User();
        user.setId(customer.getId());
        user.setParentId(customer.getParentId());
        user.setUserName(customer.getName());
        user.setLoginName(customer.getNickName());
        user.setTel(customer.getTel());
        user.setStatus(customer.getStatus());
        user.setPassword(BlowfishCipher.encode("test123"));
        logger.info("新增用户----" + customer.toString());
        userService.save(user);
        logger.info("用户信息新增成功");
    }

    @Override
    public Customer getCustomer(String id, String userId) throws Exception{
        if (StringUtils.isBlank(id)) {
            throw new Exception("客户代码不能为空");
        }
        return customerMapper.getCustomer(id, userId);
    }

    private final void checkData(Customer customer) throws Exception{
        if (StringUtils.isBlank(customer.getName())) {
            throw new Exception("请输入客户名称");
        }
        if (StringUtils.isBlank(customer.getType())) {
            throw new Exception("请选择客户类型");
        }
        if (StringUtils.isBlank(customer.getStatus())) {
            throw new Exception("请选择客户状态");
        }
        if (StringUtils.isBlank(customer.getParentId())) {
            throw new Exception("系统异常:上级用户不能为空");
        }
        if (StringUtils.isBlank(customer.getId())) {
            throw new Exception("系统异常:客户代码不能为空");
        }
    }

    private final void transfer(List<Customer> list) {
        for (Customer customer : list) {
            customer.setType(CustomerType.getLabel(customer.getType()));
            customer.setStatus(CustomerStatus.getLabel(customer.getStatus()));
        }
    }
}
