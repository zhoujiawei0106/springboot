package cn.com.zjw.springboot.entity.purchase;

import cn.com.zjw.springboot.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Order extends BaseEntity implements Serializable {

    private String id;

    /**
     * 订单编码
     */
    private String orderNum;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 订单总价
     */
    private BigDecimal totalPrice;

    /**
     * 商品单价
     * @return
     */
    private BigDecimal price;

    /**
     * 商品数量
     * @return
     */
    private BigDecimal shopNum;

    /**
     * 创建时间
     * @return
     */
    private String createTime;

    /**
     * 更新时间
     * @return
     */
    private String updateTime;

    private String name;

    /**
     * 订单
     */
    private List<Order> orderList;

    private String priceAll;

    private String shopNumAll;

    private String nameAll;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceAll() {
        return priceAll;
    }

    public void setPriceAll(String priceAll) {
        this.priceAll = priceAll;
    }

    public String getShopNumAll() {
        return shopNumAll;
    }

    public void setShopNumAll(String shopNumAll) {
        this.shopNumAll = shopNumAll;
    }

    public String getNameAll() {
        return nameAll;
    }

    public void setNameAll(String nameAll) {
        this.nameAll = nameAll;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public String getOrderNum() {
        return this.getId();
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getShopNum() {
        return shopNum;
    }

    public void setShopNum(BigDecimal shopNum) {
        this.shopNum = shopNum;
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
}
