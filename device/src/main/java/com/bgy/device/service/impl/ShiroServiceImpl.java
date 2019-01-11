package com.bgy.device.service.impl;

import com.bgy.device.entity.*;
import com.bgy.device.mapper.*;
import com.bgy.device.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Judith
 * @date 2019/1/11 14:16
 * @description
 */
@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<SysUser> findSysUser(SysUser sysUser) {
        return sysUserMapper.findSysUser(sysUser);
    }

    @Override
    public List<SysPermission> findSysPermissionListByRoleId(int roleId) {

        List<SysPermission> sysPermissionList = new ArrayList<>();
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionMapper.findByRole(roleId);
        for(SysRolePermission sysRolePermission:sysRolePermissionList) {
            SysPermission permission = sysPermissionMapper.selectByPrimaryKey(sysRolePermission.getPermissionId());
            sysPermissionList.add(permission);
        }
        return sysPermissionList;
    }

    @Override
    public List<SysRole> findSysRoleListByUsername(String username) {
        List<SysRole> sysRoleList = new ArrayList<>();
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        SysUser user = sysUserMapper.selectOne(sysUser);
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.findBySysUser(user.getId());
        for(SysUserRole sysUserRole:sysUserRoleList) {
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(sysUserRole.getRoleId());
            sysRoleList.add(sysRole);
        }
        return sysRoleList;
    }
}
