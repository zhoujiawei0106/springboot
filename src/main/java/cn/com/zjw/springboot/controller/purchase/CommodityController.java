package cn.com.zjw.springboot.controller.purchase;

import cn.com.zjw.springboot.constants.purchase.CommodityCategory;
import cn.com.zjw.springboot.constants.purchase.CustomerStatus;
import cn.com.zjw.springboot.constants.purchase.CustomerType;
import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Customer;
import cn.com.zjw.springboot.service.purchase.CommodityService;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品管理
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/purchase/commodity")
public class CommodityController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CommodityController.class);

    @Autowired
    private CommodityService commodityService;

    /**
     * 获取商品列表
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> list(Commodity commodity, HttpServletRequest request) {
        try {
            PageInfo pageInfo = commodityService.getCommoditys(commodity, getUserId(getToken(request)));
            return success(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 保存商品信息
     * @author zhoujiawei
     * @param commodity
     * @return
     */
    @PostMapping("/save")
    public Map<String, Object> save(Commodity commodity) {
        try {
            commodityService.save(commodity);
            return success("商品新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 更新商品信息
     * @author zhoujiawei
     * @param commodity
     * @param request
     * @return
     */
    @PutMapping("/update")
    public Map<String, Object> update(Commodity commodity, javax.servlet.http.HttpServletRequest request) {
        try {
            commodityService.update(commodity, getUserId(getToken(request)));
            return success("商品修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 加载商品信息
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/getCustomer")
    public Map<String, Object> getCustomer(String id, javax.servlet.http.HttpServletRequest request) {
        try {
            Commodity commodity = commodityService.getCommodity(id, getUserId(getToken(request)));
            return success(commodity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 删除商品信息
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Map<String, Object> delete(String id, javax.servlet.http.HttpServletRequest request) {
        try {
            commodityService.delete(id, getUserId(getToken(request)));
            return success("商品删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 获取商品类型常量
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/commodityCategory")
    public Map<String, Object> commodityCategory() {
        try {
            return success(CommodityCategory.getCommodityCategory());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

}
