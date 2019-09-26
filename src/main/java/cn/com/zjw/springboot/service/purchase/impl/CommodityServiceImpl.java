package cn.com.zjw.springboot.service.purchase.impl;

import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.mapper.purchase.CommodityMapper;
import cn.com.zjw.springboot.service.purchase.CommodityService;
import cn.com.zjw.springboot.service.system.UserService;
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
public class CommodityServiceImpl implements CommodityService {

    private Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public PageInfo getCommoditys(Commodity commodity, String userId) {
        PageHelper.startPage(commodity.getPage(), commodity.getRows());
        logger.info("根据条件查询所有用户----" + commodity.toString());
        List<Commodity> list = commodityMapper.getCommoditys(commodity, userId);
        /*transfer(list);*/
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

}
