package cn.com.zjw.springboot.service.purchase.impl;

import cn.com.zjw.springboot.entity.purchase.Commodity;
import cn.com.zjw.springboot.entity.purchase.Schedule;
import cn.com.zjw.springboot.mapper.purchase.ScheduleMapper;
import cn.com.zjw.springboot.service.purchase.ScheduleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * 客户管理
 * @author zhoujiawei
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ScheduleServiceImpl implements ScheduleService {

    private Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public PageInfo getSchedules(Schedule schedule, String userId) {
        PageHelper.startPage(schedule.getPage(), schedule.getRows());
        logger.info("根据条件查询所有商品----" + schedule.toString());
        List<Schedule> list = scheduleMapper.getSchedules(schedule, userId);
        /*transfer(list);*/
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void save(Schedule schedule) {
        logger.info("新增行程----" + schedule.toString());
        /*schedule.setId(UUID.randomUUID().toString());*/
        schedule.setStatus("0");
        scheduleMapper.save(schedule);
        logger.info("行程信息新增成功");
    }

    @Override
    public Schedule getSchedule(String id, String userId) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("行程id不能为空");
        }
        if (StringUtils.isBlank(userId)) {
            logger.error("系统异常，上级用户代码为空");
            throw new Exception("系统异常，上级用户代码为空");
        }

        Schedule schedule = scheduleMapper.getSchedule(id, userId);
        if (schedule == null) {
            throw new Exception("无法获取行程信息");
        }
        logger.info(schedule.toString());

        return schedule;
    }

    @Override
    public void update(Schedule schedule, String userId) throws Exception {
        if (StringUtils.isBlank(schedule.getId())) {
            throw new Exception("请选择一条记录");
        }
        logger.info("修改行程信息-----" + schedule.toString());
        scheduleMapper.update(schedule);
        logger.info("行程信息修改成功");
    }

    @Override
    public void scheduleEnd(String id, String userId) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("请选择一条记录");
        }
        logger.info("结束行程-----" + id);
        scheduleMapper.scheduleEnd(id);
        logger.info("行程结束成功");
    }


}
