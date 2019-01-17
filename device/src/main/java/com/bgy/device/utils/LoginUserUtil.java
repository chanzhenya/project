/**
* <p>Title: LoginController.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: http://www.bgy.com.cn/</p>  
* @author 李强  liqiang149@countrygarden.com.cn
* @date 2018年8月22日  
* @version 1.0  
*/
package com.bgy.device.utils;

import com.bgy.device.entity.SysUser;
/**
 * 
* <p>Title: LoginUserUtil</p>  
* <p>Description: 登录用户信息工具类</p>  
* @author 李强  liqiang149@countrygarden.com.cn
* @date 2018年8月27日
 */
public class LoginUserUtil {
	
	private static ThreadLocal<String> localLoginUser = new ThreadLocal<String>();
	
	public static void setLocalLoginUser(String username) {
		localLoginUser.set(username);
	}

	/**
	 * 
	 * @Title: getUserInfo 
	 * @Description: 获取当前登录用户信息
	 * @return
	 */
	public static String getUserInfo() {
		return localLoginUser.get();//从Thread local本地线程获取SysUser用户对象
	}
	
}
