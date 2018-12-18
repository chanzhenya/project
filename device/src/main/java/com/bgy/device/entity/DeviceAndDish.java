package com.bgy.device.entity;

import javax.persistence.*;

@Table(name = "device_and_dish")
public class DeviceAndDish {
    @Id
    private String id;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "dish_id")
    private String dishId;

    @Column(name = "dish_name")
    private String dishName;

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
     * @return device_id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
     * @return dish_name
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * @param dishName
     */
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
}