package com.bgy.robot.accesscontrol.entity;

import javax.persistence.*;

public class Account {
    /**
     * 工号
     */
    @Id
    @Column(name = "JOB_NO")
    private String jobNo;

    /**
     * 姓名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 性别
     */
    @Column(name = "GENDER")
    private Integer gender;

    /**
     * 职称
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 照片路径
     */
    @Column(name = "PHOTO_PATH")
    private String photoPath;

    /**
     * 特征值
     */
    @Column(name = "EIGENVALUE")
    private String eigenvalue;

    /**
     * 获取工号
     *
     * @return JOB_NO - 工号
     */
    public String getJobNo() {
        return jobNo;
    }

    /**
     * 设置工号
     *
     * @param jobNo 工号
     */
    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    /**
     * 获取姓名
     *
     * @return NAME - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别
     *
     * @return GENDER - 性别
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取职称
     *
     * @return TITLE - 职称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置职称
     *
     * @param title 职称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取照片路径
     *
     * @return PHOTO_PATH - 照片路径
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * 设置照片路径
     *
     * @param photoPath 照片路径
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    /**
     * 获取特征值
     *
     * @return EIGENVALUE - 特征值
     */
    public String getEigenvalue() {
        return eigenvalue;
    }

    /**
     * 设置特征值
     *
     * @param eigenvalue 特征值
     */
    public void setEigenvalue(String eigenvalue) {
        this.eigenvalue = eigenvalue;
    }
}