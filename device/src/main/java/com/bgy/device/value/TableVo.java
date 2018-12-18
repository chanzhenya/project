package com.bgy.device.value;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Judith
 * @date 2018/12/15
 */
@Data
public class TableVo implements Serializable {

    private String tableId;

    private String tableName;

    private String positionCode;

    private String deviceId;

    private String deviceName;
}
