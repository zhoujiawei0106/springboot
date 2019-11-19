package cn.com.zjw.springboot.constants.enumConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum CustomerStatus {
    Agency("1", "有效"),
    Customer("2", "注销");

    private String value;

    private String label;

    private CustomerStatus(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getLabel(String value) {
        for (CustomerStatus customerStatus : CustomerStatus.values()) {
            if (customerStatus.getValue().equals(value)) {
                return customerStatus.getLabel();
            }
        }
        return "";
    }

    public static List<Map<String, String>> getCustomerStatus() {
        List<Map<String, String>> list = new ArrayList<>();
        for (CustomerStatus customerStatus : CustomerStatus.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", customerStatus.getValue());
            map.put("label", customerStatus.getLabel());
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
