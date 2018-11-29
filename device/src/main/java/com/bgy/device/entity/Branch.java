package com.bgy.device.entity;

import javax.persistence.*;

public class Branch {
    @Id
    @Column(name = "branch_id")
    private Integer branchId;

    /**
     * 门店名称
     */
    @Column(name = "branch_name")
    private String branchName;

    /**
     * 门店编号
     */
    @Column(name = "branch_no")
    private String branchNo;

    /**
     * @return branch_id
     */
    public Integer getBranchId() {
        return branchId;
    }

    /**
     * @param branchId
     */
    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    /**
     * 获取门店名称
     *
     * @return branch_name - 门店名称
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * 设置门店名称
     *
     * @param branchName 门店名称
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * 获取门店编号
     *
     * @return branch_no - 门店编号
     */
    public String getBranchNo() {
        return branchNo;
    }

    /**
     * 设置门店编号
     *
     * @param branchNo 门店编号
     */
    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }
}