package cn.com.zjw.springboot.service.purchase.impl;

import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Inventory;
import cn.com.zjw.springboot.mapper.purchase.InventoryMapper;
import cn.com.zjw.springboot.service.purchase.InventoryService;
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
public class InventoryServiceImpl implements InventoryService {

    private Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public PageInfo getInventorys(Inventory inventory, String userId) {
        PageHelper.startPage(inventory.getPage(), inventory.getRows());
        logger.info("根据条件查询所有库存商品----" + inventory.toString());
        List<Inventory> list = inventoryMapper.getInventorys(inventory, userId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void update(Inventory inventory, String userId) throws Exception {
        if (StringUtils.isBlank(inventory.getId())) {
            throw new Exception("请选择一条记录");
        }
        logger.info("修改库存信息-----" + inventory.toString());
        inventory.setShopNum(inventory.getShopNum().add(getInventory(inventory.getId(),userId).getShopNum()));
        inventoryMapper.update(inventory);
        logger.info("库存信息修改成功");
    }

    @Override
    public Inventory getInventory(String id, String userId) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("库存商品id不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            logger.error("系统异常，上级用户代码为空");
            throw new Exception("系统异常，上级用户代码为空");
        }

        Inventory inventory = inventoryMapper.getInventory(id, userId);
        if (inventory == null) {
            throw new Exception("无法获取库存商品信息");
        }
        logger.info(inventory.toString());

        return inventory;
    }

    @Override
    public void reset(String id) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("库存销毁商品id不能为空");
        }
        logger.info("库存销毁的商品id为-----" + id);
        inventoryMapper.reset(id);
        logger.info("库存销毁成功");
    }

    @Override
    public List<Inventory> export(Inventory inventory, String userId) {
        logger.info("根据条件导出所有库存信息----" + inventory.toString());
        List<Inventory> list = inventoryMapper.getInventorys(inventory, userId);
        logger.info("导出的商品数据共 " + list.size() + "条");
        return list;
    }

}
