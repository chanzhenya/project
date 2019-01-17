package com.bgy.device.controller;

import com.bgy.device.common.CommonCoreContent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Judith
 * @date 2019/1/17 16:39
 * @description
 */
public abstract class BaseController {

    protected String getToken() {
        String token = null;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies(); //获取token
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                //取出token值
                if (cookie.getName().equals(CommonCoreContent.TOKEN_KEY)) {
                    token = cookie.getValue();
                }

            }
        }
        return token;
    }
}
