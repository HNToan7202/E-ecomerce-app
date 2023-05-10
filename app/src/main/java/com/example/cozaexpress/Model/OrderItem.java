package com.example.cozaexpress.Model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private String id;
    private Order order;
    private Product product;
    private Integer count;
    private String createat;
    private String updateat;
    private Double Totalprice;


    public OrderItem() {
    }

    public Double getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(Double totalprice) {
        Totalprice = totalprice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCreateat() {
        return createat;
    }

    public void setCreateat(String createat) {
        this.createat = createat;
    }

    public String getUpdateat() {
        return updateat;
    }

    public void setUpdateat(String updateat) {
        this.updateat = updateat;
    }
}
