package cn.com.zjw.springboot.service.purchase.impl;

import cn.com.zjw.springboot.constants.enumConstants.CommodityCategory;
import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Inventory;
import cn.com.zjw.springboot.mapper.purchase.CommodityMapper;
import cn.com.zjw.springboot.mapper.purchase.InventoryMapper;
import cn.com.zjw.springboot.service.purchase.CommodityService;
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
import java.util.Date;
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

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public PageInfo getCommoditys(Commodity commodity, String userId) {
        PageHelper.startPage(commodity.getPage(), commodity.getRows());
        logger.info("根据条件查询所有商品----" + commodity.toString());
        List<Commodity> list = commodityMapper.getCommoditys(commodity, userId);
        transfer(list);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void save(Commodity commodity) {
        logger.info("新增商品----" + commodity.toString());
        commodityMapper.save(commodity);
        logger.info("商品信息新增成功");
        logger.info("新增库存----   商品名称：" + commodity.getName() +
                "、英文名称：" + commodity.getEnName() + "、商品数量:" + commodity.getShopNum());
        //设置后台行程编号IN+YYMMddHHmmss
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYMMddHHmmss");
        String inventoryId = "IN" + sdf.format(date);
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
        logger.info("修改客户信息-----" + commodity.toString());
        commodityMapper.update(commodity);
        logger.info("客户信息修改成功");
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
        commodityMapper.delete(id);
        logger.info("删除商品成功");
        inventoryMapper.delete(commodity.getId());
        logger.info("删除库存成功");
    }

    @Override
    public List<Commodity> export(Commodity commodity, String userId) {
        logger.info("根据条件导出所有商品信息----" + commodity.toString());
        List<Commodity> list = commodityMapper.getCommoditys(commodity, userId);
        transfer(list);
        logger.info("导出的商品数据共 " + list.size() + "条");
        return list;
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
