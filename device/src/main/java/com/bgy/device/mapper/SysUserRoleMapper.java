package com.bgy.device.mapper;

import com.bgy.device.entity.SysUserRole;
import com.bgy.device.utils.MyMapper;

import java.util.List;

public interface SysUserRoleMapper extends MyMapper<SysUserRole> {

    List<SysUserRole> findBySysUser(Integer userId);
}