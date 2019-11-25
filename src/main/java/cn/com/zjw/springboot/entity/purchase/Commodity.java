package cn.com.zjw.springboot.entity.purchase;

import cn.com.zjw.springboot.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Commodity extends BaseEntity implements Serializable {

    private String id;

    /**
     * 商品名称（前端格式所需）
     */
    private String value;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String enName;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品价格范围（至多）
     */
    private BigDecimal priceMax;

    /**
     * 商品成本
     */
    private BigDecimal basePrice;

    /**
     * 商品类型
     */
    private String category;

    /**
     * 商品品牌
     */
    private String brand;

    /**
     * 商品数量
     */
    private BigDecimal shopNum;

    /**
     * 有效状态 0.无效 1.有效
     */
    private String isDelete;

    @Override
    public String toString() {
        return "Commodity{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", name='" + name + '\'' +
                ", enName='" + enName + '\'' +
                ", price='" + price + '\''  +
                ", priceMax='" + priceMax + '\''  +
                ", basePrice='" + basePrice + '\''  +
                ", category='" + category + '\''  +
                ", brand='" + brand + '\''  +
                ", isDelete='" + isDelete + '\''  +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public BigDecimal getShopNum() {
        return shopNum;
    }

    public void setShopNum(BigDecimal shopNum) {
        this.shopNum = shopNum;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(BigDecimal priceMax) {
        this.priceMax = priceMax;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
