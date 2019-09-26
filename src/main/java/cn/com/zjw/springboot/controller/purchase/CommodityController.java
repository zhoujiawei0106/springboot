package cn.com.zjw.springboot.controller.purchase;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.service.purchase.CommodityService;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
