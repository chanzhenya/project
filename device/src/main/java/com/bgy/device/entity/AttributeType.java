package com.bgy.device.entity;

import javax.persistence.*;

@Table(name = "attribute_type")
public class AttributeType {
    @Id
    @Column(name = "attribute_type_id")
    private Integer attributeTypeId;

    @Column(name = "attribute_type_name")
    private String attributeTypeName;

    /**
     * @return attribute_type_id
     */
    public Integer getAttributeTypeId() {
        return attributeTypeId;
    }

    /**
     * @param attributeTypeId
     */
    public void setAttributeTypeId(Integer attributeTypeId) {
        this.attributeTypeId = attributeTypeId;
    }

    /**
     * @return attribute_type_name
     */
    public String getAttributeTypeName() {
        return attributeTypeName;
    }

    /**
     * @param attributeTypeName
     */
    public void setAttributeTypeName(String attributeTypeName) {
        this.attributeTypeName = attributeTypeName;
    }
}