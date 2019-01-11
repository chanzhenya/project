package com.bgy.device.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;

/**
 * @author Judith
 * @date 2019/1/11 11:53
 * @description
 */
@Slf4j
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {

        String className = ae.getClass().getName();
        String message;
        String username = getUsername(request);
        super.setFailureAttribute(request, ae);
        if (UnknownAccountException.class.getName().equals(className)) {
            log.info("对用户[{}]进行登录验证..验证未通过,未知账户", username);
            message = "账户不存在";
        } else if (IncorrectCredentialsException.class.getName().equals(className)) {
            log.info("对用户[{}]进行登录验证..验证未通过,错误的凭证", username);
            message = "密码不正确";
        } else if(LockedAccountException.class.getName().equals(className)) {
            log.info("对用户[{}]进行登录验证..验证未通过,账户已锁定", username);
            message = "账户已锁定";
        } else if(ExcessiveAttemptsException.class.getName().equals(className)) {
            log.info("对用户[{}]进行登录验证..验证未通过,错误次数过多", username);
            message = "用户名或密码错误次数过多，请十分钟后再试";
        } else if (AuthenticationException.class.getName().equals(className)) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("对用户[{}]进行登录验证..验证未通过,未知错误", username);
            message = "用户名或密码不正确";
        } else{
            message = className;
        }
        request.setAttribute(getFailureKeyAttribute(),message);
    }
}
