package cn.com.zjw.springboot.entity.purchase;

import cn.com.zjw.springboot.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Inventory extends BaseEntity implements Serializable {

    private String id;

    /**
     * 库存商品名称
     */
    private String name;

    /**
     * 库存商品英文名称
     */
    private String enName;

    /**
     * 入库时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 库存数量
     */
    private BigDecimal shopNum;

    /**
     * 商品id
     */
    private String commodityId;


    @Override
    public String toString() {
        return "Inventory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", shopNum='" + shopNum + '\''  +
                ", eName='" + enName + '\'' +
                '}';
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
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

    public BigDecimal getShopNum() {
        return shopNum;
    }

    public void setShopNum(BigDecimal shopNum) {
        this.shopNum = shopNum;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
}
