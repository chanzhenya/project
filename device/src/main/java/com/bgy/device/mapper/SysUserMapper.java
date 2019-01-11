package com.bgy.device.mapper;

import com.bgy.device.entity.SysUser;
import com.bgy.device.utils.MyMapper;

import java.util.List;

public interface SysUserMapper extends MyMapper<SysUser> {

    public List<SysUser> findSysUser(SysUser sysUser);
}