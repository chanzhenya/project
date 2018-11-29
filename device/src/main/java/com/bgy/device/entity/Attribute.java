package com.bgy.device.entity;

import javax.persistence.*;

public class Attribute {
    @Id
    @Column(name = "attribute_id")
    private Integer attributeId;

    /**
     * 属性名称
     */
    @Column(name = "attribute_name")
    private String attributeName;

    /**
     * 属性值
     */
    @Column(name = "attribute_value")
    private String attributeValue;

    /**
     * 属性类型
     */
    @Column(name = "attribute_type_id")
    private Integer attributeTypeId;

    /**
     * @return attribute_id
     */
    public Integer getAttributeId() {
        return attributeId;
    }

    /**
     * @param attributeId
     */
    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    /**
     * 获取属性名称
     *
     * @return attribute_name - 属性名称
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * 设置属性名称
     *
     * @param attributeName 属性名称
     */
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    /**
     * 获取属性值
     *
     * @return attribute_value - 属性值
     */
    public String getAttributeValue() {
        return attributeValue;
    }

    /**
     * 设置属性值
     *
     * @param attributeValue 属性值
     */
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    /**
     * 获取属性类型
     *
     * @return attribute_type_id - 属性类型
     */
    public Integer getAttributeTypeId() {
        return attributeTypeId;
    }

    /**
     * 设置属性类型
     *
     * @param attributeTypeId 属性类型
     */
    public void setAttributeTypeId(Integer attributeTypeId) {
        this.attributeTypeId = attributeTypeId;
    }
}