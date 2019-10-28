package cn.com.zjw.springboot.constants.purchase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum OrderStatus {
    Order_Success("1", "订单下单成功"),
    Order_NoInventory("2", "订单下单成功库存不足"),
    Order_Done("3", "订单已完成"),
    Order_back("4", "订单撤销成功"),
    Order_BackOfPart("5", "订单部分撤销成功"),
    ;

    private String value;

    private String label;

    private OrderStatus(String value, String label) {
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
