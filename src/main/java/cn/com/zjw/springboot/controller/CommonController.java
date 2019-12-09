package cn.com.zjw.springboot.controller;

import cn.com.zjw.springboot.constants.enumConstants.CustomerType;
import cn.com.zjw.springboot.constants.enumConstants.ValidStatus;
import cn.com.zjw.springboot.controller.system.RoleController;
import cn.com.zjw.springboot.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 公用请求controller
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private CommonService commonService;

    /**
     * 获取有效状态常量
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/customerStatus")
    public Map<String, Object> customerStatus() {
        try {
            return success(ValidStatus.getCustomerStatus());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 获取客户类型常量
     * @author zhoujiawei
     * @param request
     * @return
     */
    @GetMapping("/customerType")
    public Map<String, Object> customerType(HttpServletRequest request) {
        try {

            return success(commonService.customerType(getUserId(getToken(request))));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }
}
