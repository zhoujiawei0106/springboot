package cn.com.zjw.springboot.entity.purchase;

import cn.com.zjw.springboot.entity.BaseEntity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Customer extends BaseEntity implements Serializable {

    private String id;

    /**
     * 客户名称
     */
    private String name;

    /**
     *
     */
    private String parentId;

    /**
     * 客户电话
     */
    private Long tel;

    /**
     * 客户地址
     */
    private String address;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 客户积分
     */
    private Long point;

    /**
     * 客户昵称
     */
    private String nickname;

    /**
     * 客户类型(customer_type)
     */
    private Integer type;

    /**
     * 客户状态(customer_status)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Timestamp createTimestamp;

    /**
     * 修改时间
     */
    private Timestamp updateTimestamp;

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", idcard='" + idcard + '\'' +
                ", point='" + point + '\'' +
                ", nickname='" + nickname + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", createTimestamp='" + createTimestamp + '\'' +
                ", updateTimestamp='" + updateTimestamp + '\'' +
                '}';
    }

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}
