package cn.com.zjw.springboot.constants.enumConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum ScheduleStatus {
    noDone("0","未开始"),
    ing("1", "进行中"),
    gone("2", "已完成");

    private String value;

    private String label;

    private ScheduleStatus(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getLabel(String value) {
        for (ScheduleStatus scheduleStatus : ScheduleStatus.values()) {
            if (scheduleStatus.getValue().equals(value)) {
                return scheduleStatus.getLabel();
            }
        }
        return "";
    }

    public static List<Map<String, String>> getScheduleStatus() {
        List<Map<String, String>> list = new ArrayList<>();
        for (ScheduleStatus scheduleStatus : ScheduleStatus.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", scheduleStatus.getValue());
            map.put("label", scheduleStatus.getLabel());
            list.add(map);
        }
        return list;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
