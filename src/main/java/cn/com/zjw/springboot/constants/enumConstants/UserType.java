package cn.com.zjw.springboot.constants.enumConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum UserType {

    Admin("1", "系统管理员"),
    User("2", "普通用户");

    private String value;

    private String label;

    UserType(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getLabel(String value) {
        for (UserType customerType : UserType.values()) {
            if (customerType.getValue().equals(value)) {
                return customerType.getLabel();
            }
        }
        return "";
    }

    public static List<Map<String, String>> getCustomerType() {
        List<Map<String, String>> list = new ArrayList<>();
        for (UserType customerType : UserType.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", customerType.getValue());
            map.put("label", customerType.getLabel());
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
