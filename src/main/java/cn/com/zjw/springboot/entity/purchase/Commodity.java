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
     *商品描述/详细
     */
    private String description;

    /**
     * 商品图片
     */
    private String pictureUrl;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品价格范围（至多）
     */
    private BigDecimal priceOf;

    /**
     * 商品成本
     */
    private BigDecimal baseprice;

    /**
     * 商品类型
     */
    private String type;

    /**
     * 商品品牌
     */
    private String brand;

    @Override
    public String toString() {
        return "Commodity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", price='" + price + '\''  +
                ", priceOf='" + priceOf + '\''  +
                ", baseprice='" + baseprice + '\''  +
                ", type='" + type + '\''  +
                ", brand='" + brand + '\''  +
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceOf() {
        return priceOf;
    }

    public void setPriceOf(BigDecimal priceOf) {
        this.priceOf = priceOf;
    }

    public BigDecimal getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(BigDecimal baseprice) {
        this.baseprice = baseprice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
