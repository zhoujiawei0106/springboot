package cn.com.zjw.springboot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuDto implements Serializable  {

    private String id;

    private String name;

    private String url;

    private List<MenuDto> subMenus = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuDto> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<MenuDto> subMenus) {
        this.subMenus = subMenus;
    }
}
