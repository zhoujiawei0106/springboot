package cn.com.zjw.springboot.entity.purchase;

import cn.com.zjw.springboot.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Inventory extends BaseEntity implements Serializable {

    private String id;

    /**
     * 库存商品名称
     */
    private String name;

    /**
     * 库存商品描述
     */
    private String description;

    /**
     * 入库时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 入库人
     */
    private String createMan;

    /**
     * 商品来源
     */
    private String shopAddress;

    /**
     * 是否上架
     */
    private String whetherShelf;

    /**
     * 库存数量
     */
    private BigDecimal shopNum;


    @Override
    public String toString() {
        return "Inventory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", shopNum='" + shopNum + '\''  +
                ", description='" + description + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\''  +
                ", createMan='" + createMan + '\''  +
                ", shopAddress='" + shopAddress + '\''  +
                ", whetherShelf='" + whetherShelf + '\''  +
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getWhetherShelf() {
        return whetherShelf;
    }

    public void setWhetherShelf(String whetherShelf) {
        this.whetherShelf = whetherShelf;
    }

    public BigDecimal getShopNum() {
        return shopNum;
    }

    public void setShopNum(BigDecimal shopNum) {
        this.shopNum = shopNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
