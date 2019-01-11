package com.bgy.device.shiro.realm;

import com.bgy.device.entity.SysPermission;
import com.bgy.device.entity.SysRole;
import com.bgy.device.entity.SysUser;
import com.bgy.device.service.ShiroService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Judith
 * @date 2019/1/11 10:16
 * @description
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("[---------- 身份认证方法：MyShiroRealm.doGetAuthenticationInfo() ----------]");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());

        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);

        List<SysUser> sysUsers = shiroService.findSysUser(sysUser);
        SysUser user = null;
        if(sysUsers.size() > 0) {
            user = sysUsers.get(0);
        }
        if(null == user) {
            throw new AccountException("账号密码不正确！");
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        for(SysRole sysRole:shiroService.findSysRoleListByUsername(sysUser.getUsername())) {
            authenticationInfo.addRole(sysRole.getRolename());
            log.info("[----------- ",sysRole.toString()," -----------]");

            for (SysPermission sysPermission:shiroService.findSysPermissionListByRoleId(sysRole.getId())) {
                log.info("[------------",sysPermission.toString(), "-----------]");
            }
        }
        return authenticationInfo;
    }
}
