package com.bgy.device.entity;

import javax.persistence.*;

@Table(name = "sys_role")
public class SysRole {
    @Id
    private Integer id;

    private String rolename;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return rolename
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * @param rolename
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}