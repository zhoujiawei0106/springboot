package cn.com.zjw.springboot.service.purchase.impl;

import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.mapper.purchase.CustomerMapper;
import cn.com.zjw.springboot.service.purchase.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo getCustomers(Customer customer) {
        PageHelper.startPage(customer.getPage(), customer.getRows());
        logger.info("根据条件查询所有用户----" + customer.toString());
        List<Customer> list = customerMapper.getCustomers(customer);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
