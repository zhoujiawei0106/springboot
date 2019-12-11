package cn.com.zjw.springboot.service.purchase.impl;

import cn.com.zjw.springboot.constants.CodeConstants;
import cn.com.zjw.springboot.constants.enumConstants.CustomerType;
import cn.com.zjw.springboot.constants.enumConstants.ValidStatus;
import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.entity.system.User;
import cn.com.zjw.springboot.mapper.purchase.CustomerMapper;
import cn.com.zjw.springboot.mapper.system.UserMapper;
import cn.com.zjw.springboot.service.purchase.CustomerService;
import cn.com.zjw.springboot.service.system.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Autowired
    private UserMapper userMapper;

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
    public List<Customer> getCustomersOfOrder(Customer customer, String userId) {
        logger.info("根据条件查询所有用户----" + customer.toString());
        List<Customer> list = customerMapper.getCustomers(customer, userId);
        transfer(list);
        return list;
    }

    @Override
    public void save(Customer customer, String userId, Date expiringDate) throws Exception {
        checkData(customer);

        // 根据登陆用户的不同，做不同用户的数据校验
        User loginUser = userMapper.getUserById(userId);
        if (CodeConstants.USER_TYPE_ADMIN.equals(loginUser.getUserType())) {
            // 超级用户新增/修改客户时，客户有效期不能为空
            if (expiringDate == null) {
                throw new Exception("请选择客户有效期");
            }
        } else if (CodeConstants.USER_TYPE_NORMAL.equals(loginUser.getUserType())) {
            Customer loginCustomer = customerMapper.getByLoginUser(userId);
            // 代理和代购不能创建或修改平级用户的操作
            if (CustomerType.Purchaser.getValue().equals(loginCustomer.getType()) &&
                    CustomerType.Purchaser.getValue().equals(customer.getType())) {
                throw new Exception("代购用户只能操作代理或顾客信息");
            } else if (CustomerType.Agency.getValue().equals(loginCustomer.getType()) &&
                    !CustomerType.Customer.getValue().equals(customer.getType())) {
                throw new Exception("代理用户只能操作顾客信息");
            } else {
                throw new Exception("登陆的用户不允许进行此操作");
            }
        }

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
        user.setPassword(user.getLoginName());
        // 管理员用户使用页面传入的有效日期，普通用户用当前登陆用户的日期
        if (CodeConstants.USER_TYPE_ADMIN.equals(loginUser.getUserType())) {
            user.setExpiringDate(expiringDate);
        } else {
            user.setExpiringDate(loginUser.getExpiringDate());
        }
        logger.info("新增客户-----" + customer.toString());
        userService.save(user);
        logger.info("客户信息新增成功");
    }

    @Override
    public Customer getCustomer(String id, String userId) throws Exception{
        if (StringUtils.isBlank(id)) {
            throw new Exception("客户代码不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            logger.error("系统异常，上级用户代码为空");
            throw new Exception("系统异常，上级用户代码为空");
        }

        Customer customer = customerMapper.getCustomer(id, userId);
        if (customer == null) {
            throw new Exception("无法获取客户信息");
        }

        logger.info(customer.toString());

        return customer;
    }

    @Override
    public void update(Customer customer, String userId, Date expiringDate) throws Exception {
        if (StringUtils.isBlank(customer.getId())) {
            throw new Exception("请选择一条记录");
        }

        // 校验客户信息是否存在
        getCustomer(customer.getId(), userId);

        checkData(customer);

        logger.info("修改客户信息-----" + customer.toString());
        customerMapper.update(customer);
        logger.info("客户信息修改成功");

        userService.updateByCustomer(customer);
        logger.info("用户信息修改成功");
    }

    @Override
    public void delete(String id, String userId) throws Exception{
        if (StringUtils.isBlank(id)) {
            throw new Exception("客户代码不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            throw new Exception("上级客户代码不能为空");
        }

        // 校验客户信息是否存在
        Customer customer = getCustomer(id, userId);

        logger.info("删除的客户信息-----" + customer);
        customerMapper.delete(id);
        logger.info("删除客户成功");

        logger.info("删除用户信息的id-----" + id);
        userService.delete(id);
        logger.info("删除用户成功");
    }

    @Override
    public List<Customer> export(Customer customer, String userId) {
        logger.info("根据条件查询所有用户----" + customer.toString());
        List<Customer> list = customerMapper.getCustomers(customer, userId);
        transfer(list);
        logger.info("导出的客户数据共 " + list.size() + "条");
        return list;
    }

    @Override
    public Object getUserType(String userId) {
        User user = userMapper.getUserById(userId);
        if (CodeConstants.USER_TYPE_ADMIN.equals(user.getUserType())) {
            return false;
        } else if (CodeConstants.USER_TYPE_NORMAL.equals(user.getUserType())) {
            return user;
        } else {
            return user;
        }
    }

    /**
     * 数据校验
     * @author zhoujiawei
     * @param customer
     * @return
     * @throws Exception
     */
    private final void checkData(Customer customer) throws Exception{
        if (StringUtils.isBlank(customer.getName())) {
            throw new Exception("请输入客户名称");
        }
        if (StringUtils.isBlank(customer.getNickName())) {
            throw new Exception("请输入客户昵称");
        }
        if (customer.getTel() == null) {
            throw new Exception("请输入客户电话");
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

    /**
     * 翻译
     * @author zhoujiawei
     * @param list
     */
    private final void transfer(List<Customer> list) {
        for (Customer customer : list) {
            customer.setType(CustomerType.getLabel(customer.getType()));
            customer.setStatus(ValidStatus.getLabel(customer.getStatus()));
        }
    }

}
