package cn.com.zjw.springboot.entity.purchase;

import cn.com.zjw.springboot.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Commodity extends BaseEntity implements Serializable {

    private String id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String eName;

    /**
     *商品描述/详细
     */
    private String description;

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

    @Override
    public String toString() {
        return "Commodity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", eName='" + eName + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\''  +
                ", priceMax='" + priceMax + '\''  +
                ", basePrice='" + basePrice + '\''  +
                ", category='" + category + '\''  +
                ", brand='" + brand + '\''  +
                '}';
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

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
