/**
* <p>Title: LoginController.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: http://www.bgy.com.cn/</p>  
* @author 李强  liqiang149@countrygarden.com.cn
* @date 2019年1月15日  
* @version 1.0  
*/
package com.bgy.device.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bgy.device.entity.SysUser;
import com.bgy.device.redis.RedisService;
import com.bgy.device.redis.utils.RedisKeyUtil;
import com.bgy.device.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bgy.device.service.LoginService;
import com.bgy.device.value.ResultVo;

/**  
* <p>Title: LoginController</p>  
* <p>Description: </p>  
* @author 李强  liqiang149@countrygarden.com.cn
* @date 2019年1月15日  
*/
@Controller
public class LoginController extends BaseController {
	@Autowired
	private LoginService loginService;

	@Autowired
	private RedisService redisService;

	/**
	 * 
	 * <p>Title: login</p>  
	 * <p>Description: 前端登录方法</p>  
	 * @param response
	 * @param params 用户名，动态密码
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResultVo login(HttpServletResponse response, 
    		@RequestBody Map<String,String> params) {
        return loginService.loginCheck(params, response);
    }
    
    /**
     * 
     * <p>Title: sendDynamicPassword</p>  
     * <p>Description: 根据手机号发送动态验证码</p>  
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dynamicPWD/{phone}", method = RequestMethod.GET)
    public ResultVo sendDynamicPassword(@PathVariable("phone") String phone) {
    	
        return loginService.sendDynamicPassword(phone);
    }

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout() {
    	try {
    		String token = getToken();
    		String tokenKey = RedisKeyUtil.getTokenKey(token);
    		String username = redisService.get(tokenKey);
    		String userKey = RedisKeyUtil.getUserKey(username);
			redisService.deleteKey(tokenKey,userKey);
		} catch (Exception e) {
    		e.printStackTrace();
		}
		return "redirect:/login";
	}
}
