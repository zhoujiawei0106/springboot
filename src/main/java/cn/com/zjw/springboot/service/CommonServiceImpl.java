package cn.com.zjw.springboot.service;

import cn.com.zjw.springboot.constants.enumConstants.CustomerType;
import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.mapper.purchase.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Map<String, String>> customerType(String userId) throws Exception {
        userId = "eecef4d42acfcb439eb81dd0565ed852";
        Customer customer = customerMapper.getByLoginUser(userId);
        List<Map<String, String>> list = CustomerType.getCustomerType();

        // 没有客户信息，登陆用户为管理员或高权限用户
        if (customer == null) {
            return list;
        } else {
            List<Map<String, String>> copyList = new ArrayList<>();
            copyList.addAll(list);
            if (customer.getType().equals(CustomerType.Purchaser.getValue())) {
                for (Map<String, String> map : list) {
                    if (map.get("value").equals(CustomerType.Purchaser.getValue())) {
                        copyList.remove(map);
                    }
                }
            } else if (customer.getType().equals(CustomerType.Agency.getValue())) {
                for (Map<String, String> map : list) {
                    if (!map.get("value").equals(CustomerType.Customer.getValue())) {
                        copyList.remove(map);
                    }
                }
            }
            return copyList;
        }
    }
}
