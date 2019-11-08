package cn.com.zjw.springboot.entity.purchase;

import cn.com.zjw.springboot.entity.BaseEntity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Schedule extends BaseEntity implements Serializable {

    private static final DateTimeFormatter dfr = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    private String id;

    /**
     * 行程编码
     */
    private String scheduleNum;

    /**
     * 行程状态
     */
    private String status;

    /**
     * 行程状态
     */
    private String place;

    /**
     * 创建时间
     * @return
     */
    private String startTime;

    /**
     * 结束时间
     * @return
     */
    private String endTime;

    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + id + '\'' +
                ", scheduleNum='" + scheduleNum + '\'' +
                ", status='" + status + '\'' +
                ", place='" + place + '\'' +
                ", startTime='" + startTime + '\''  +
                ", endTime='" + endTime + '\''  +
                '}';
    }

    public String getStartTime() throws Exception {
        return startTime;
    }

    public void setStartTime(String startTime) throws Exception {
        try {
            this.startTime = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(startTime));
        } catch (ParseException e) {
            throw new Exception("日期格式错误");
        }
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) throws Exception {
        try {
            this.endTime = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
        } catch (ParseException e) {
            throw new Exception("日期格式错误");
        }
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScheduleNum() {
        return this.getId();
    }

    public void setScheduleNum(String scheduleNum) {
        this.scheduleNum = this.getId();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
