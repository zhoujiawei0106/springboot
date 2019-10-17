package cn.com.zjw.springboot.entity.purchase;

import cn.com.zjw.springboot.entity.BaseEntity;

import java.io.Serializable;

public class Schedule extends BaseEntity implements Serializable {

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
     * 更新时间
     * @return
     */
    private String endTime;

    @Override
    public String toString() {
        return "Commodity{" +
                "id='" + id + '\'' +
                ", scheduleNum='" + scheduleNum + '\'' +
                ", status='" + status + '\'' +
                ", place='" + place + '\'' +
                ", startTime='" + startTime + '\''  +
                ", endTime='" + endTime + '\''  +
                '}';
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
