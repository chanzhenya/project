/**
* <p>Title: BaseException.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: http://www.bgy.com.cn/</p>  
* @author 李强  liqiang149@countrygarden.com.cn
* @date 2019年1月15日  
* @version 1.0  
*/
package com.bgy.device.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**  
* <p>Title: BaseException</p>  
* <p>Description: 通用公共异常</p>  
* @author 李强  liqiang149@countrygarden.com.cn
* @date 2019年1月15日  
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException{
	
	/** serialVersionUID*/  
	private static final long serialVersionUID = 8983192643661298403L;
	//异常信息
	private String msg;
	

}
