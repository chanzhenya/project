package com.bgy.device.entity;

import javax.persistence.*;

@Table(name = "device_and_table")
public class DeviceAndTable {
    @Id
    @Column(name = "table_id")
    private String tableId;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "position_code")
    private String positionCode;

    /**
     * 餐桌名称
     */
    @Column(name = "table_name")
    private String tableName;

    /**
     * 是否通过云轨送餐的餐桌：0-不是，1-是
     */
    private Boolean flag;

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
     * 获取餐桌名称
     *
     * @return table_name - 餐桌名称
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置餐桌名称
     *
     * @param tableName 餐桌名称
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 获取是否通过云轨送餐的餐桌：0-不是，1-是
     *
     * @return flag - 是否通过云轨送餐的餐桌：0-不是，1-是
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置是否通过云轨送餐的餐桌：0-不是，1-是
     *
     * @param flag 是否通过云轨送餐的餐桌：0-不是，1-是
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}