package com.bgy.device.value;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Judith
 * @date 2018/12/13
 */
@Data
public class DeviceVo implements Serializable {

    private String deviceId;

    private String deviceName;

    private String ip;

    private Integer port;

    private String pid;

    private String pdeviceName;

    private Integer online;

    private String branchId;

    private String deviceType;

    private String deviceTypeName;

    private Integer stationType;

    private Integer stationNo;

    private Integer stationPno;

    private String positionCode;
}
