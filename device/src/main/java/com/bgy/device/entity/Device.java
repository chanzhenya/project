package com.bgy.device.entity;

import lombok.Data;

import javax.persistence.*;

@Data
public class Device {
    @Id
    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "device_name")
    private String deviceName;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 端口号
     */
    private Integer port;

    /**
     * 父节点，如所处倍速链ID
     */
    private String pid;

    /**
     * 是否在线，0-不在线，1-在线
     */
    private Integer online;

    /**
     * 门店id
     */
    @Column(name = "branch_id")
    private Integer branchId;

    /**
     * 设备类型
     */
    @Column(name = "device_type_id")
    private Integer deviceTypeId;

    /**
     * 附加属性
     */
    @Column(name = "attribute_id")
    private Integer attributeId;

    /**
     * 设备序号
     */
    @Column(name = "device_index")
    private Integer deviceIndex;
}