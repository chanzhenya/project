package com.bgy.device.entity;

import javax.persistence.*;

@Table(name = "dish_product_relation")
public class DishProductRelation {
    @Id
    private String id;

    @Column(name = "dish_id")
    private String dishId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return dish_id
     */
    public String getDishId() {
        return dishId;
    }

    /**
     * @param dishId
     */
    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    /**
     * @return product_id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
}