package cn.com.zjw.springboot.entity;

public class Menu {

    private String id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单路由
     */
    private String menuRoutePath;

    /**
     * 父菜单
     */
    private String pid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuRoutePath() {
        return menuRoutePath;
    }

    public void setMenuRoutePath(String menuRoutePath) {
        this.menuRoutePath = menuRoutePath;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
