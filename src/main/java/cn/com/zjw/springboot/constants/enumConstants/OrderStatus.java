package cn.com.zjw.springboot.constants.enumConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum OrderStatus {
    Order_FALSE("0", "未支付"),
    Order_NoInventory("1", "已支付"),
    ;

    private String value;

    private String label;

    OrderStatus(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getLabel(String value) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.getValue().equals(value)) {
                return orderStatus.getLabel();
            }
        }
        return "";
    }

    public static List<Map<String, String>> getOrderStatus() {
        List<Map<String, String>> list = new ArrayList<>();
        for (OrderStatus orderStatus : OrderStatus.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", orderStatus.getValue());
            map.put("label", orderStatus.getLabel());
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
