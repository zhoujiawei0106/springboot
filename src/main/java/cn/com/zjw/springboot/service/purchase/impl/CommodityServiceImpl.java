package cn.com.zjw.springboot.service.purchase.impl;

import cn.com.zjw.springboot.constants.enumConstants.CommodityCategory;
import cn.com.zjw.springboot.constants.enumConstants.CustomerType;
import cn.com.zjw.springboot.constants.enumConstants.ValidStatus;
import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.entity.purchase.Inventory;
import cn.com.zjw.springboot.mapper.purchase.CommodityMapper;
import cn.com.zjw.springboot.mapper.purchase.CustomerMapper;
import cn.com.zjw.springboot.mapper.purchase.InventoryMapper;
import cn.com.zjw.springboot.mapper.purchase.OrderMapper;
import cn.com.zjw.springboot.service.purchase.CommodityService;
import cn.com.zjw.springboot.utils.CommonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public PageInfo getCommoditys(Commodity commodity, String userId) {
        PageHelper.startPage(commodity.getPage(), commodity.getRows());
        logger.info("根据条件查询所有商品----" + commodity.toString());
        //先暂时userId为空，后面根据类型改
        commodity.setIsValid(String.valueOf(ValidStatus.Valid.getValue()));
        Customer customer = customerMapper.getCustomerd(userId);
        List<Commodity> list;
        if(customer == null){
            list = commodityMapper.getCommoditys(null, commodity, userId);
        } else if(CustomerType.getLabel(customer.getType()).equals(CustomerType.Purchaser)){
            list = commodityMapper.getCommoditys(customer, commodity, null);
        } else {
            list = commodityMapper.getCommoditys(customer, commodity, userId);
        }
        transfer(list);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public Commodity getCommodity(String id, String userId) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("商品id不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            logger.error("系统异常，上级用户代码为空");
            throw new Exception("系统异常，上级用户代码为空");
        }
        Commodity commodity = commodityMapper.getCommodity(id, userId);
        if (commodity == null) {
            throw new Exception("无法获取商品信息");
        }
        logger.info(commodity.toString());

        return commodity;
    }

    @Override
    public void save(Commodity commodity, String userId) throws Exception {
        //设置后台行程编号IN+YYMMddHHmmss
        /*Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYMMddHHmmss");
        String commodityId = "CM" + sdf.format(date);*/
        //校验商品
        commodityCheck(commodity, userId);
        String commodityId = CommonUtils.getUUID();
        commodity.setId(commodityId);
        logger.info("新增商品----" + commodity.toString());
        commodity.setIsValid("1");
        commodityMapper.save(commodity, userId);
        logger.info("商品信息新增成功");
        logger.info("新增库存----   商品名称：" + commodity.getName() +
                "、英文名称：" + commodity.getEnName() + "、商品数量:" + commodity.getShopNum());
        /*String inventoryId = "IN" + sdf.format(date);*/
        String inventoryId = CommonUtils.getUUID();
        Inventory inventory = new Inventory();
        inventory.setId(inventoryId);
        inventory.setShopNum(commodity.getShopNum());
        inventory.setCommodityId(commodity.getId());
        inventoryMapper.save(inventory);
        logger.info("库存信息新增成功");
    }

    @Override
    public void update(Commodity commodity, String userId) throws Exception {
        if (StringUtils.isBlank(commodity.getId())) {
            throw new Exception("请选择一条记录");
        }
        //校验商品
        commodityCheck(commodity, userId);
        Commodity cm = getCommodity(commodity.getId(), userId);
        logger.info("修改商品信息-----" + commodity.toString());
        //判断商品是否在订单中使用
        List<Commodity> commodityList = orderMapper.getCommodityForOrder(null,commodity.getId(),userId);
        if(commodityList.size()<1) {
            commodityMapper.update(commodity, userId);
            logger.info("商品信息修改成功");
        } else {
            //删除商品信息
            commodityMapper.delete(cm.getId(), userId);
            logger.info("商品信息删除成功");
            //设置后台行程编号IN+YYMMddHHmmss
            /*Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("YYMMddHHmmss");
            String commodityId = "CM" + sdf.format(date);*/
            String commodityId = CommonUtils.getUUID();
            commodity.setId(commodityId);
            commodity.setIsValid("1");
            commodityMapper.save(commodity, userId);
            /*String inventoryId = "IN" + sdf.format(date);*/
            String inventoryId = CommonUtils.getUUID();
            Inventory inventory = new Inventory();
            inventory.setId(inventoryId);
            inventory.setShopNum(commodity.getShopNum());
            inventory.setCommodityId(commodity.getId());
            inventoryMapper.save(inventory);
            logger.info("库存信息新增成功");
        }
    }

    @Override
    public void delete(String id, String userId) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("商品id不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            throw new Exception("上级客户代码不能为空");
        }

        // 校验商品信息是否存在
        Commodity commodity = getCommodity(id, userId);
        if(commodity.getShopNum().compareTo(BigDecimal.ZERO) > 0) {
            throw new Exception("请先销毁库存");
        }
        logger.info("删除的商品信息-----" + commodity);
        commodityMapper.delete(id, userId);
        logger.info("删除商品成功");
    }

    @Override
    public List<Commodity> export(Commodity commodity, String userId) {
        logger.info("根据条件导出所有商品信息----" + commodity.toString());
        List<Commodity> list = commodityMapper.getCommoditys(null, commodity, userId);
        transfer(list);
        logger.info("导出的商品数据共 " + list.size() + "条");
        return list;
    }

    /**
     * 商品非空校验
     * @param commodity
     * @param userId
     * @throws Exception
     */
    private void commodityCheck(Commodity commodity, String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            throw new Exception("当前用户信息不存在");
        }
        if (StringUtils.isBlank(commodity.getName())) {
            throw new Exception("商品名称不能为空");
        }
        if (StringUtils.isBlank(commodity.getEnName())) {
            throw new Exception("商品英文名称不能为空");
        }
        if (StringUtils.isBlank(commodity.getCategory())) {
            throw new Exception("商品类型不能为空");
        }
        if (StringUtils.isBlank(commodity.getBrand())) {
            throw new Exception("商品品牌不能为空");
        }
    }

    /**
     * 翻译
     * @author zhoujiawei
     * @param list
     */
    private final void transfer(List<Commodity> list) {
        for (Commodity commodity : list) {
            commodity.setCategory(CommodityCategory.getLabel(commodity.getCategory()));
        }
    }

}
