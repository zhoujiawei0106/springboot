package cn.com.zjw.springboot.controller.purchase;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.purchase.Inventory;
import cn.com.zjw.springboot.service.purchase.InventoryService;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * 库存管理
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/purchase/inventory")
public class InventoryController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    /**
     * 获取库存商品列表
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> list(Inventory inventory, HttpServletRequest request) {
        try {
            PageInfo pageInfo = inventoryService.getInventorys(inventory, getUserId(getToken(request)));
            return success(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 更新商品信息
     * @author zhoujiawei
     * @param inventory
     * @param request
     * @return
     */
    @PutMapping("/update")
    public Map<String, Object> update(Inventory inventory, javax.servlet.http.HttpServletRequest request) {
        try {
            inventoryService.update(inventory, getUserId(getToken(request)));
            return success("库存商品修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 加载库存商品信息
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/getInventory")
    public Map<String, Object> getInventory(String id, HttpServletRequest request) {
        try {
            Inventory inventory = inventoryService.getInventory(id, getUserId(getToken(request)));
            return success(inventory);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 库存销毁
     * @author zhoujiawei
     * @param id
     * @return
     */
    @PutMapping("/reset")
    public Map<String, Object> reset(String id) {
        try {
            inventoryService.reset(id);
            return success("库存销毁成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }
}
