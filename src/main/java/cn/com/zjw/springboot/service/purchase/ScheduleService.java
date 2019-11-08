package cn.com.zjw.springboot.service.purchase;

import cn.com.zjw.springboot.entity.purchase.Schedule;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;

/**
 * 行程管理
 * @author zhoujiawei
 */
public interface ScheduleService {

    /**
     * 获取行程列表
     * @author zhoujiawei
     * @param schedule
     * @param userId
     * @return
     */
    public PageInfo getSchedules(Schedule schedule, String userId) throws Exception;

    /**
     * 保存行程信息
     * @param schedule
     */
    public void save(Schedule schedule) throws Exception;

    /**
     * 加载行程信息
     * @param id
     * @param userId
     * @return
     */
    public Schedule getSchedule(String id, String userId) throws Exception;

    /**
     * 更新行程信息
     * @param schedule
     * @param userId
     */
    public void update(Schedule schedule, String userId) throws Exception;

    /**
     * 行程结束
     * @param id
     * @param userId
     */
    public void scheduleEnd(String id, String userId) throws Exception;
}
