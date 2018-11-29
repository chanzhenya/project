package com.bgy.device.value;

import lombok.Data;

@Data
public class DeviceVo {

    private String deviceId;

    private Integer index;

    private String deviceName;

    private String ip;

    private Integer port;

    private String pid;

    private Integer online;

    private Integer branchId;

    private String branchName;

    private String branchNo;

    private Integer deviceTypeId;

    private String deviceTypeName;

    private String deviceTypeCode;

    private String attributeId;

    private String attributeName;

    private String attributeValue;
}
