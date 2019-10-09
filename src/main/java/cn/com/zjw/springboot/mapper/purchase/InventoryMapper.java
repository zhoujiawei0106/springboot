package cn.com.zjw.springboot.mapper.purchase;

import cn.com.zjw.springboot.entity.purchase.Inventory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryMapper {



    /**
     * 获取库存商品列表
     * @author zhoujiawei
     * @param inventory
     * @param userId
     * @return
     */
    public List<Inventory> getInventorys(@Param("inventory") Inventory inventory, @Param("userId") String userId);

    /**
     * 添加库存商品
     * @param inventory
     */
    public void save(@Param("inventory") Inventory inventory,@Param("userId") String userId);

    /**
     * 删除库存商品
     * @param id
     */
    public void delete(String id);

    /**
     * 修改库存商品
     * @param inventory
     */
    public void update(@Param("inventory") Inventory inventory);

    /**
     * 获取库存信息
     * @param id
     * @param userId
     * @return
     */
    public Inventory getInventory(@Param("id") String id, String userId);
}
