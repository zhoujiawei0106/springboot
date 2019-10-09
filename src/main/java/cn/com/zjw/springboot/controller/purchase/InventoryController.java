package cn.com.zjw.springboot.controller.purchase;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Inventory;
import cn.com.zjw.springboot.service.purchase.CommodityService;
import cn.com.zjw.springboot.service.purchase.InventoryService;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
     * 保存库存商品信息
     * @author zhoujiawei
     * @param inventory
     * @return
     */
    @PostMapping("/save")
    public Map<String, Object> save(Inventory inventory, HttpServletRequest request) {
        try {
            String userId = getUserId(getToken(request));
            inventoryService.save(inventory,userId);
            return success("库存商品新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 删除库存商品信息
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Map<String, Object> delete(String id, HttpServletRequest request) {
        try {
            inventoryService.delete(id, getUserId(getToken(request)));
            return success("库存商品删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }
}
