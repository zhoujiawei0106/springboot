package cn.com.zjw.springboot.service.purchase.impl;

import cn.com.zjw.springboot.constants.enumConstants.ScheduleStatus;
import cn.com.zjw.springboot.entity.purchase.Schedule;
import cn.com.zjw.springboot.mapper.purchase.ScheduleMapper;
import cn.com.zjw.springboot.service.purchase.ScheduleService;
import cn.com.zjw.springboot.utils.DateUtlis;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public PageInfo getSchedules(Schedule schedule, String userId) throws Exception {
        PageHelper.startPage(schedule.getPage(), schedule.getRows());
        logger.info("根据条件查询所有商品----" + schedule.toString());
        //时间戳
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        //判断日期是否为非空
        Date startTime = null;
        Date endTime = null;
        if(StringUtils.isNotBlank(schedule.getStartTime())) {
            startTime = df.parse(schedule.getStartTime());
        }
        if(StringUtils.isNotBlank(schedule.getEndTime())) {
            endTime = df.parse(schedule.getEndTime());
        }
        //行程列表查询
        List<Schedule> list = scheduleMapper.getSchedules(schedule, startTime, endTime, userId);
        //翻译（状态）
        transfer(list);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void save(Schedule schedule) throws Exception {
        logger.info("新增行程----" + schedule.toString());
        if(schedule.getStartTime().compareTo(DateUtlis.systemTimeLocal(null)) < 0) {
            throw new Exception("行程开始时间不能小于现在的北京时间");
        }
        if(schedule.getStartTime().compareTo(schedule.getEndTime()) > 0) {
            throw new Exception("行程开始时间不能小于行程结束时间");
        }
        //非空校验
        ScheduleCheck(schedule);
        //设置后台行程编号SD+YYMMddHHmmss
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYMMddHHmmss");
        String orderId = "SD" + sdf.format(date);
        schedule.setId(orderId);
        //时间戳转换格式
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startTime = df.parse(schedule.getStartTime());
            Date endTime = df.parse(schedule.getEndTime());
            scheduleMapper.save(schedule, startTime, endTime);
            logger.info("行程信息新增成功");
        } catch (ParseException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public void update(Schedule schedule, String userId) throws Exception {
        if (StringUtils.isBlank(schedule.getId())) {
            throw new Exception("请选择一条记录");
        }
        ScheduleCheck(schedule);
        if(schedule.getStartTime().compareTo(schedule.getEndTime()) > 0) {
            throw new Exception("行程开始时间不能小于行程结束时间");
        }
        if(schedule.getEndTime().compareTo(DateUtlis.systemTimeLocal(null)) < 0) {
            throw new Exception("行程结束时间不能小于现在北京时间");
        }

        //时间戳
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startTime = df.parse(schedule.getStartTime());
            Date endTime = df.parse(schedule.getEndTime());
            logger.info("修改行程信息-----" + schedule.toString());
            scheduleMapper.update(schedule, startTime, endTime);
        } catch (ParseException e) {
            logger.error(e.getMessage(),e);
        }
        logger.info("行程信息修改成功");
    }

    @Override
    public void scheduleEnd(String id, String userId) throws Exception {
        if (StringUtils.isBlank(id)) {
            throw new Exception("请选择一条记录");
        }
        Schedule schedule = getSchedule(id, userId);
        ScheduleCheck(schedule);
        if (!schedule.getStatus().equals("0")) {
            throw new Exception("行程进行中, 无法删除");
        }
        logger.info("结束行程-----" + id);
        if(schedule.getStatus().equals('0')) {
            throw new Exception("行程已开始, 无法删除");
        }
        scheduleMapper.scheduleEnd(id);
        logger.info("行程结束成功");
    }

    @Override
    public List<Schedule> export(Schedule schedule, String userId) throws Exception {
        logger.info("根据条件查询所有行程----" + schedule.toString());
        //时间戳
        SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        //判断日期是否为非空
        Date startTime = null;
        Date endTime = null;
        if(StringUtils.isNotBlank(schedule.getStartTime())) {
            startTime = df.parse(schedule.getStartTime());
        }
        if(StringUtils.isNotBlank(schedule.getEndTime())) {
            endTime = df.parse(schedule.getEndTime());
        }
        List<Schedule> list = scheduleMapper.getSchedules(schedule, startTime, endTime, userId);
        transfer(list);
        logger.info("导出的行程数据共 " + list.size() + "条");
        return list;
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

    /**
     * 非空校验
     * @param schedule
     * @throws Exception
     */
    private void ScheduleCheck(Schedule schedule) throws Exception {
        if (StringUtils.isBlank(schedule.getStartTime())) {
            throw new Exception("行程开始时间不能为空");
        }
        if (StringUtils.isBlank(schedule.getEndTime())) {
            throw new Exception("行程结束时间不能为空");
        }
        if (StringUtils.isBlank(schedule.getPlace())) {
            throw new Exception("行程目的地不能为空");
        }
    }

    /**
     * 翻译
     * @author zhoujiawei
     * @param list
     */
    private final void transfer(List<Schedule> list) {
        for (Schedule schedule : list) {
            schedule.setStatus(ScheduleStatus.getLabel(schedule.getStatus()));
        }
    }
}
