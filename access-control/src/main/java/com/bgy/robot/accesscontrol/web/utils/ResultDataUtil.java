package com.bgy.robot.accesscontrol.web.utils;

import com.bgy.robot.accesscontrol.web.value.ResultData;

/**
 * 返回结果处理类
 */
public class ResultDataUtil {

    /**
     * 请求成功
     * @param obj
     * @param token
     * @return
     */
    public static ResultData success(Object obj, String token){
        ResultData resultData=new ResultData();
        resultData.setStatus(true);
        resultData.setCode(200);
        resultData.setMsg("Success");
        resultData.setToken(token);
        resultData.setData(obj);
        return resultData;
    }

    /**
     * 请求失败
     * @param msg
     * @return
     */
    public static ResultData error(String msg){
        ResultData resultData=new ResultData();
        resultData.setStatus(false);
        resultData.setMsg(msg);
        resultData.setCode(500);
        return resultData;
    }
}
