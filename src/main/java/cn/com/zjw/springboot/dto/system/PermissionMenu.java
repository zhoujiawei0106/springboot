package cn.com.zjw.springboot.dto.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 登陆用户菜单
 * @author zhoujiawei
 */
public class PermissionMenu implements Serializable {

    private String id;

    private String pid;

    private String label;

    private String type;

    private List<PermissionMenu> children = new ArrayList<>();

    @Override
    public String toString() {
        return "PermissionMenu{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                ", children=" + children +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PermissionMenu> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionMenu> children) {
        this.children = children;
    }
}
