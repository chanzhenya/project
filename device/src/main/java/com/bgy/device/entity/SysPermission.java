package com.bgy.device.entity;

import javax.persistence.*;

@Table(name = "sys_permission")
public class SysPermission {
    @Id
    private Integer id;

    private String permissionname;

    @Column(name = "role_id")
    private Integer roleId;

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
     * @return permissionname
     */
    public String getPermissionname() {
        return permissionname;
    }

    /**
     * @param permissionname
     */
    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", permissionname='" + permissionname + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}