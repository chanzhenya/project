package com.bgy.device.entity;

import javax.persistence.*;

@Table(name = "table_info")
public class TableInfo {
    @Id
    @Column(name = "table_id")
    private String tableId;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "position_code")
    private String positionCode;

    @Column(name = "device_id")
    private String deviceId;

    /**
     * @return table_id
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * @param tableId
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * @return table_name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return branch_id
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * @param branchId
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * @return position_code
     */
    public String getPositionCode() {
        return positionCode;
    }

    /**
     * @param positionCode
     */
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
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
}