package cn.com.zjw.springboot.mapper.purchase;

import cn.com.zjw.springboot.entity.purchase.Schedule;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ScheduleMapper {

    /**
     * 获取行程列表
     * @author zhoujiawei
     * @param schedule
     * @param userId
     * @return
     */
    public List<Schedule> getSchedules(@Param("schedule") Schedule schedule,
                                       @Param("startTime") Date startTime,
                                       @Param("endTime") Date endTime,
                                       @Param("userId") String userId);

    /**
     * 新增行程
     * @param schedule
     */
    public void save(@Param("schedule") Schedule schedule,@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 获取行程信息
     * @param id
     * @param userId
     * @return
     */
    public Schedule getSchedule(@Param("id") String id, String userId);

    /**
     * 修改行程信息
     * @param schedule
     */
    public void update(@Param("schedule") Schedule schedule, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 结束行程
     * @param id
     */
    public void scheduleEnd(String id);
}
