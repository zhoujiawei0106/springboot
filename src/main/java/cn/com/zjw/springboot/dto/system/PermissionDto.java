package cn.com.zjw.springboot.dto.system;

import java.io.Serializable;

public class PermissionDto implements Serializable {

    private String label;

    private String key;

    private String pinyin;

    private String permissionId;

    @Override
    public String toString() {
        return "PermissionDto{" +
                "label='" + label + '\'' +
                ", key='" + key + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
