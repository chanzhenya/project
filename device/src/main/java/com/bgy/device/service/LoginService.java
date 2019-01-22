/**
* <p>Title: LoginService.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: http://www.bgy.com.cn/</p>  
* @author 李强  liqiang149@countrygarden.com.cn
* @date 2019年1月15日  
* @version 1.0  
*/
package com.bgy.device.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import com.bgy.device.value.ResultVo;


/**  
* <p>Title: LoginService</p>  
* <p>Description: 登录校验服务</p>  
* @author 李强  liqiang149@countrygarden.com.cn
* @date 2019年1月15日  
*/
public interface LoginService {
	/**
	 * 
	 * <p>Title: loginCheck</p>  
	 * <p>Description: 登录验证</p>  
	 * @param params 前端输入参数，包含手机号，动态密码
	 * @param response 回写token使用
	 * @return ResultVo 登录成功/失败
	 */
	public ResultVo loginCheck(Map<String,String> params, HttpServletResponse response) ;
	
	/**
	 * 
	 * <p>Title: sendDynamicPassword</p>  
	 * <p>Description: 前端输入手机号码，发送动态密码</p>  
	 * @param phone 手机号
	 * @return ResultVo 发送成功/失败
	 */
	public ResultVo sendDynamicPassword(String phone);
}
