package cn.com.zjw.springboot.controller.purchase;

import cn.com.zjw.springboot.controller.BaseController;
import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Schedule;
import cn.com.zjw.springboot.service.purchase.CommodityService;
import cn.com.zjw.springboot.service.purchase.ScheduleService;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 商品管理
 * @author zhoujiawei
 */
@RestController
@RequestMapping("/purchase/schedule")
public class ScheduleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    /**
     * 获取行程列表
     * @author zhoujiawei
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> list(Schedule schedule, HttpServletRequest request) {
        try {
            PageInfo pageInfo = scheduleService.getSchedules(schedule, getUserId(getToken(request)));
            return success(pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 保存行程
     * @author zhoujiawei
     * @param schedule
     * @return
     */
    @PostMapping("/save")
    public Map<String, Object> save(Schedule schedule) {
        try {
            scheduleService.save(schedule);
            return success("行程新增成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 加载行程信息
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/getSchedule")
    public Map<String, Object> getSchedule(String id, HttpServletRequest request) {
        try {
            Schedule schedule = scheduleService.getSchedule(id, getUserId(getToken(request)));
            return success(schedule);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 更新行程信息
     * @author zhoujiawei
     *
     * @param schedule
     * @param request
     * @return
     */
    @PutMapping("/update")
    public Map<String, Object> update(Schedule schedule, javax.servlet.http.HttpServletRequest request) {
        try {
            scheduleService.update(schedule, getUserId(getToken(request)));
            return success("行程修改成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }

    /**
     * 行程结束
     * @author zhoujiawei
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping("/delete")
    public Map<String, Object> scheduleEnd(String id, javax.servlet.http.HttpServletRequest request) {
        try {
            scheduleService.scheduleEnd(id, getUserId(getToken(request)));
            return success("商品删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return fail(e.getMessage());
        }
    }
}
