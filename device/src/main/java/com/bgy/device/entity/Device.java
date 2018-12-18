package com.bgy.device.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
     * 是否在线/是否空闲，0-不在线，1-在线；0-忙碌中，1-空闲
     */
    private Integer online;

    /**
     * 门店id
     */
    @Column(name = "branch_id")
    private String branchId;

    /**
     * 设备类型
     */
    @Column(name = "device_type")
    private String deviceType;

    /**
     * 工位类型：0-非工位；1-主工位；2-次工位
     */
    @Column(name = "station_type")
    private Integer stationType;

    /**
     * 次工位号
     */
    @Column(name = "station_no")
    private Integer stationNo;

    /**
     * 主工位号
     */
    @Column(name = "station_pno")
    private Integer stationPno;

    /**
     * 上下机构坐标
     */
    @Column(name = "position_code")
    private String positionCode;

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
     * @return device_name
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * @param deviceName
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * 获取ip地址
     *
     * @return ip - ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip地址
     *
     * @param ip ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取端口号
     *
     * @return port - 端口号
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 设置端口号
     *
     * @param port 端口号
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 获取父节点，如所处倍速链ID
     *
     * @return pid - 父节点，如所处倍速链ID
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置父节点，如所处倍速链ID
     *
     * @param pid 父节点，如所处倍速链ID
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取是否在线/是否空闲，0-不在线，1-在线；0-忙碌中，1-空闲
     *
     * @return online - 是否在线/是否空闲，0-不在线，1-在线；0-忙碌中，1-空闲
     */
    public Integer getOnline() {
        return online;
    }

    /**
     * 设置是否在线/是否空闲，0-不在线，1-在线；0-忙碌中，1-空闲
     *
     * @param online 是否在线/是否空闲，0-不在线，1-在线；0-忙碌中，1-空闲
     */
    public void setOnline(Integer online) {
        this.online = online;
    }

    /**
     * 获取门店id
     *
     * @return branch_id - 门店id
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * 设置门店id
     *
     * @param branchId 门店id
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * 获取设备类型
     *
     * @return device_type - 设备类型
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * 设置设备类型
     *
     * @param deviceType 设备类型
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * 获取工位类型：0-非工位；1-主工位；2-次工位
     *
     * @return station_type - 工位类型：0-非工位；1-主工位；2-次工位
     */
    public Integer getStationType() {
        return stationType;
    }

    /**
     * 设置工位类型：0-非工位；1-主工位；2-次工位
     *
     * @param stationType 工位类型：0-非工位；1-主工位；2-次工位
     */
    public void setStationType(Integer stationType) {
        this.stationType = stationType;
    }

    /**
     * 获取次工位号
     *
     * @return station_no - 次工位号
     */
    public Integer getStationNo() {
        return stationNo;
    }

    /**
     * 设置次工位号
     *
     * @param stationNo 次工位号
     */
    public void setStationNo(Integer stationNo) {
        this.stationNo = stationNo;
    }

    /**
     * 获取主工位号
     *
     * @return station_pno - 主工位号
     */
    public Integer getStationPno() {
        return stationPno;
    }

    /**
     * 设置主工位号
     *
     * @param stationPno 主工位号
     */
    public void setStationPno(Integer stationPno) {
        this.stationPno = stationPno;
    }

    /**
     * 获取上下机构坐标
     *
     * @return position_code - 上下机构坐标
     */
    public String getPositionCode() {
        return positionCode;
    }

    /**
     * 设置上下机构坐标
     *
     * @param positionCode 上下机构坐标
     */
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
}