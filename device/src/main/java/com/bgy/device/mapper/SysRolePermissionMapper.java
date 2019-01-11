package com.bgy.device.mapper;

import com.bgy.device.entity.SysRolePermission;
import com.bgy.device.utils.MyMapper;

import java.util.List;

public interface SysRolePermissionMapper extends MyMapper<SysRolePermission> {

    List<SysRolePermission> findByRole(int roleId);
}