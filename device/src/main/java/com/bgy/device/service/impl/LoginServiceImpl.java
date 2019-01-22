/**
* <p>Title: LoginServiceImpl.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: http://www.bgy.com.cn/</p>  
* @author 李强  liqiang149@countrygarden.com.cn
* @date 2019年1月15日  
* @version 1.0  
*/
package com.bgy.device.service.impl;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.bgy.device.entity.SysUser;
import com.bgy.device.mapper.SysUserMapper;
import com.bgy.device.redis.utils.RedisKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bgy.device.common.CommonCoreContent;
import com.bgy.device.redis.RedisService;
import com.bgy.device.service.LoginService;
import com.bgy.device.utils.CookieUtils;
import com.bgy.device.utils.IdUtil;
import com.bgy.device.utils.RSAUtils;
import com.bgy.device.utils.ResultUtil;
import com.bgy.device.value.ResultVo;

/**  
* <p>Title: LoginServiceImpl</p>  
* <p>Description: 登录校验服务</p>  
* @author 李强  liqiang149@countrygarden.com.cn
* @date 2019年1月15日  
*/
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private RedisService redisServer;

	@Autowired
	private SysUserMapper sysUserMapper;
	
//	@Autowired
//	private TokenService tokenService;
	

	
	
	/* (non-Javadoc)  
	 * <p>Title: loginCheck</p>  
	 * <p>Description: 登录验证</p>  
	 * @param params 前端输入参数，包含手机号，动态密码
	 * @param response 回写token使用
	 * @return ResultVo 登录成功/失败
	 * @see com.bgy.device.service.LoginService#loginCheck(java.util.Map, javax.servlet.http.HttpServletResponse)  
	 */
	@Override
	public ResultVo loginCheck(Map<String, String> params, HttpServletResponse response) {
		String phone = params.get("username");
		String password = params.get("password");
		if (StringUtils.isBlank(phone) || StringUtils.isBlank(password)) {
			return ResultUtil.error("手机号、动态密码必填");
		}
		//RSA解密
		password = decryptPWD(password);
		String key = CommonCoreContent.LOGIN_CACHE_PREFIX + phone;
		String smsCode = redisServer.get(key);
		if (StringUtils.isBlank(smsCode)) {
			//动态密码过期或者未生成过
			return ResultUtil.error("未生成动态密码");
		}
		if (!StringUtils.equalsIgnoreCase(password, smsCode)) {
			//动态密码过期或者未生成过
			return ResultUtil.error("密码错误");
		}
		//验证成功，删除Redis密码,密码一次有效
		redisServer.deleteKey(key);
		//一个账号只允许一个地方在线，失效旧的token
		String oldToken = redisServer.get(RedisKeyUtil.getUserKey(phone));
		redisServer.deleteKey(RedisKeyUtil.getUserKey(phone),RedisKeyUtil.getTokenKey(oldToken));
		//登录成功创建分布式令牌
		String token = UUID.randomUUID().toString();
		redisServer.setWithExpire(RedisKeyUtil.getUserKey(phone),token,CommonCoreContent.LOGIN_EXPIRE_TIME);
		redisServer.setWithExpire(RedisKeyUtil.getTokenKey(token),phone,CommonCoreContent.LOGIN_EXPIRE_TIME);
		CookieUtils.setTokenCookie(CommonCoreContent.TOKEN_KEY, token, (int) CommonCoreContent.LOGIN_EXPIRE_TIME, response);
		return ResultUtil.success("登录成功");
	}

	/* (non-Javadoc)  
	 * <p>Title: sendDynamicPassword</p>  
	 * <p>Description: 前端输入手机号码，发送动态密码</p>  
	 * @param phone 手机号
	 * @return ResultVo 发送成功/失败
	 * @see com.bgy.device.service.LoginService#sendDynamicPassword(java.lang.String)  
	 */
	@Override
	public ResultVo sendDynamicPassword(String phone) {
		if (StringUtils.isBlank(phone)) {
			return ResultUtil.error("手机号码不可为空");
		}
		//数据库校验该号码是否注册或者存在系统中
		boolean phoneExist = true;
		SysUser sysUser = sysUserMapper.findByUsername(phone);
		if(sysUser == null) {
			phoneExist = false;
		}
		if (!phoneExist) {
			return ResultUtil.error("手机号码未注册");
		}
		//随机生成6位验证码，临时使用
		String code = IdUtil.genRandomNum(6);
		//TODO 调用短信发送接口，同时存入DB记录
		System.out.println(code);//测试使用
		String key = CommonCoreContent.LOGIN_CACHE_PREFIX + phone;
		boolean status = redisServer.setWithExpire(key, code, CommonCoreContent.SMSCODE_EXPIRE_SECONDS);
		if (!status) {
			return ResultUtil.error("动态密码保存失败，请稍后重试或联系系统管理员");
		}
		return ResultUtil.success("动态密码发送成功，5分钟内有效");
	}
	/**
	 * 
	 * <p>Title: decryptPWD</p>  
	 * <p>Description: 解密前端传入的RSA加密数据</p>  
	 * @param password
	 */
	private String decryptPWD(String password){
		
		if (StringUtils.isBlank(password)) {
			return password;
		}
		String newPwd = RSAUtils.decryptDataOnJava(password, CommonCoreContent.privateKey);
	    return newPwd;
	}

}
