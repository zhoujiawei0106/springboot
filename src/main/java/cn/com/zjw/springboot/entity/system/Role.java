package cn.com.zjw.springboot.entity.system;

import cn.com.zjw.springboot.entity.BaseEntity;

import java.io.Serializable;

public class Role extends BaseEntity implements Serializable {

    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 登陆名称
     */
    private String loginName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户名
     */
    private String userName;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleName='" + loginName + '\'' +
                ", remark='" + remark + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
