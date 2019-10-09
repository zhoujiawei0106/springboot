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
    public void save(Inventory inventory, String userId) {
        logger.info("新增库存商品----" + inventory.toString());
        inventoryMapper.save(inventory, userId);
        logger.info("库存商品信息新增成功");
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
        /*Inventory inventory = getInventory(id, userId);*/

        //logger.info("删除的库存商品信息-----" + inventory);
        inventoryMapper.delete(id);
        logger.info("删除库存商品成功");
    }

    @Override
    public void update(Inventory inventory, String userId) throws Exception {
        if (StringUtils.isBlank(inventory.getId())) {
            throw new Exception("请选择一条记录");
        }
        logger.info("修改客户信息-----" + inventory.toString());
        inventoryMapper.update(inventory);
        logger.info("客户信息修改成功");
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

}
