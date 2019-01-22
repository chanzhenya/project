package com.bgy.device.aop;



import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.bgy.device.common.CommonCoreContent;
import com.bgy.device.entity.BaseException;
import com.bgy.device.entity.SysUser;
import com.bgy.device.redis.RedisService;
import com.bgy.device.redis.utils.RedisKeyUtil;
import com.bgy.device.utils.CookieUtils;
import com.bgy.device.utils.LoginUserUtil;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
* <p>Title: GetLoginIngfoAop</p>  
* <p>Description: 登录拦截</p>  
* @author 李强  liqiang149@countrygarden.com.cn
* @date 2018年9月28日
 */
@Component
@Aspect
@Order(1)
public class LoginCheckAop {

	
	 
	@Autowired
    private RedisService redisService;

	@Pointcut("within(com.bgy.device.controller..*)&&!within(com.bgy.device.controller.LoginController)")
	public void login() {}


	@Around("login()")
	public Object aroundCheckToken(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			// 获取token
			String token = getToken();
			String username = checkLoginToken(token);

			LoginUserUtil.setLocalLoginUser(username);
			// 有操作，延长登录超时时间
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getResponse();
			CookieUtils.setTokenCookie(CommonCoreContent.TOKEN_KEY, token, (int) CommonCoreContent.LOGIN_EXPIRE_TIME,
					response);
			redisService.expireKey(RedisKeyUtil.getUserKey(username), CommonCoreContent.LOGIN_EXPIRE_TIME, TimeUnit.SECONDS);
			redisService.expireKey(RedisKeyUtil.getTokenKey(token), CommonCoreContent.LOGIN_EXPIRE_TIME, TimeUnit.SECONDS);
			return joinPoint.proceed();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/login";
		}

	}

    /***
     * 通过request 获取token
     * @return
     */
    private String getToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookie = request.getCookies(); //获取token
        String token = null;
        //String requestAddress = request.getRequestURI();  //取出请求地址
        if (cookie != null) {
            for (Cookie index : cookie) {
                //取出token值
                if (index.getName().equals(CommonCoreContent.TOKEN_KEY)) {
                    token = index.getValue();
                }

            }
        }
        if (StringUtils.isBlank(token)) {
        	throw createException("请登录系统");
        }
        return token;
    }
    /**
     * 
     * <p>Title: checkLoginToken</p>  
     * <p>Description: 校验token是否有效</p>  
     * @param token
     * @return SysUser 关联的用户
     */
    public String checkLoginToken(String  token) {
		if (StringUtils.isBlank(token)) {
			throw createException("请登录系统");
		}
		
		String username = redisService.get(RedisKeyUtil.getTokenKey(token));
		if (StringUtils.isBlank(username)) {
			//已经被强制登出了
			throw createException("登录已失效");
		}
//		SysUser user = JSON.parseObject(userJson, SysUser.class);
		return username;
	}
    /**
     * 
     * <p>Title: writeMsg</p>  
     * <p>Description:写入登录信息给前端或者跳转到登录页面 </p>  
     * @param msg
     */
    private BaseException createException(String msg) {
    	return new BaseException(msg);
    }

}
