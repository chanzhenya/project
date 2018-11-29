package com.bgy.device.entity;

import javax.persistence.*;

@Table(name = "device_type")
public class DeviceType {
    /**
     * 设备类型ID
     */
    @Id
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 设备类型编号
     */
    @Column(name = "type_code")
    private String typeCode;

    /**
     * 设备类型名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 获取设备类型ID
     *
     * @return type_id - 设备类型ID
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置设备类型ID
     *
     * @param typeId 设备类型ID
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取设备类型编号
     *
     * @return type_code - 设备类型编号
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * 设置设备类型编号
     *
     * @param typeCode 设备类型编号
     */
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * 获取设备类型名称
     *
     * @return type_name - 设备类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置设备类型名称
     *
     * @param typeName 设备类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}