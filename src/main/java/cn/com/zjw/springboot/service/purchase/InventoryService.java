package cn.com.zjw.springboot.service.purchase;

import cn.com.zjw.springboot.entity.purchase.Inventory;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 库存管理
 * @author zhoujiawei
 */
public interface InventoryService {

    /**
     * 获取客户列表
     * @author zhoujiawei
     * @param inventory
     * @param userId
     * @return
     */
    public PageInfo getInventorys(Inventory inventory, String userId);

    /**
     * 修改库存商品
     * @param inventory
     * @param userId
     */
    public void update(Inventory inventory, String userId) throws Exception;

    /**
     * 获取库存信息
     * @param id
     * @param userId
     * @return
     */
    public Inventory getInventory(String id, String userId) throws Exception;

    /**
     * 库存销毁
     * @param id
     */
    public void reset(String id) throws Exception;

    /**
     * 导出报表
     * @param inventory
     * @param userId
     * @return
     */
    List<Inventory> export(Inventory inventory, String userId);
}
