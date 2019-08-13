package cn.com.zjw.springboot.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class User extends BaseEntity implements Serializable {

    private String id;

    /**
     * 登陆用户名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 登陆失败次数
     */
    private Integer loginFailTimes;

    /**
     * 登陆最后时间
     */
    private Timestamp LastLoginDate;

    private String ip;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", loginFailTimes=" + loginFailTimes +
                ", LastLoginDate=" + LastLoginDate +
                ", ip='" + ip + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLoginFailTimes() {
        return loginFailTimes;
    }

    public void setLoginFailTimes(Integer loginFailTimes) {
        this.loginFailTimes = loginFailTimes;
    }

    public Timestamp getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        LastLoginDate = lastLoginDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
