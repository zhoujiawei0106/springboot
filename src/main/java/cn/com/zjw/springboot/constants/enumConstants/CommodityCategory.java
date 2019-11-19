package cn.com.zjw.springboot.constants.enumConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum CommodityCategory {
    Food("1", "食品"),
    Tour("2", "在线旅游"),
    Library("3", "图书"),
    MonAndBaby("4", "母婴"),
    Clothes("5", "服装服饰"),
    Household ("6", "家居百货"),
    Computer("7", "电脑"),
    Digital ("8", "数码通讯"),
    Appliances("9", "家电");

    private String value;

    private String label;

    private CommodityCategory(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getLabel(String value) {
        for (CommodityCategory commodityCategory : CommodityCategory.values()) {
            if (commodityCategory.getValue().equals(value)) {
                return commodityCategory.getLabel();
            }
        }
        return "";
    }

    public static List<Map<String, String>> getCommodityCategory() {
        List<Map<String, String>> list = new ArrayList<>();
        for (CommodityCategory commodityCategory : CommodityCategory.values()) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", commodityCategory.getValue());
            map.put("label", commodityCategory.getLabel());
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
