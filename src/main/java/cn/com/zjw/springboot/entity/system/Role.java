package cn.com.zjw.springboot.entity.system;

public class Role {

    private String id;

    /**
     * 权限ID
     */
    private String premissionId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 菜单ID
     */
    private String menuId;

    /**
     * 角色名称
     */
    private String roleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPremissionId() {
        return premissionId;
    }

    public void setPremissionId(String premissionId) {
        this.premissionId = premissionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
