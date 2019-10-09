package cn.com.zjw.springboot.service.purchase;

import cn.com.zjw.springboot.entity.purchase.Inventory;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

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
     * 添加库存商品
     * @param inventory
     */
    public void save(Inventory inventory, String userId);

    /**
     * 删除库存商品
     * @param id
     * @param userId
     */
    public void delete(String id, String userId) throws Exception;

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
}
