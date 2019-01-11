package com.bgy.device.service;

import com.bgy.device.entity.SysPermission;
import com.bgy.device.entity.SysRole;
import com.bgy.device.entity.SysUser;

import java.util.List;

/**
 * @author Judith
 * @date 2019/1/11 14:15
 * @description
 */
public interface ShiroService {

    List<SysUser> findSysUser(SysUser sysUser);

    List<SysPermission> findSysPermissionListByRoleId(int roleId);

    List<SysRole> findSysRoleListByUsername(String username);
}
