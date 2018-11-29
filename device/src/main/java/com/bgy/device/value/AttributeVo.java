package com.bgy.device.value;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AttributeVo {

    private Integer attributeId;

    private String attributeName;

    private String attributeValue;

    private Integer attributeTypeId;

    private String attributeTypeName;
}
