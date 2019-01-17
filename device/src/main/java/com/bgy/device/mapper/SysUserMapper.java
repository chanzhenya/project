package com.bgy.device.mapper;

import com.bgy.device.entity.SysUser;
import com.bgy.device.utils.MyMapper;

public interface SysUserMapper extends MyMapper<SysUser> {

    public SysUser findByUsername(String username);
}