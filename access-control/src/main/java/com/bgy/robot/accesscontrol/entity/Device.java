package com.bgy.robot.accesscontrol.entity;

import javax.persistence.*;

public class Device {
    /**
     * 设备IP
     */
    @Id
    @Column(name = "IP")
    private String ip;

    /**
     * 唯一标识值
     */
    @Column(name = "GID")
    private String gid;

    /**
     * 设备DevNo
     */
    @Column(name = "DEV_NO")
    private String devNo;

    /**
     * 获取设备IP
     *
     * @return IP - 设备IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置设备IP
     *
     * @param ip 设备IP
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取唯一标识值
     *
     * @return GID - 唯一标识值
     */
    public String getGid() {
        return gid;
    }

    /**
     * 设置唯一标识值
     *
     * @param gid 唯一标识值
     */
    public void setGid(String gid) {
        this.gid = gid;
    }

    /**
     * 获取设备DevNo
     *
     * @return DEV_NO - 设备DevNo
     */
    public String getDevNo() {
        return devNo;
    }

    /**
     * 设置设备DevNo
     *
     * @param devNo 设备DevNo
     */
    public void setDevNo(String devNo) {
        this.devNo = devNo;
    }
}