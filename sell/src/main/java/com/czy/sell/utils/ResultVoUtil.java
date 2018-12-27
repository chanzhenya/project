package com.czy.sell.utils;

import com.czy.sell.VO.ResultVo;

/**
 * @author Judith
 * @date 2018/12/6
 * 返回结果处理工具类
 */
public class ResultVoUtil {

    public static ResultVo success(Object object) {
        ResultVo resultVo = new ResultVo();

        resultVo.setData(object);
        resultVo.setMsg("Success");
        resultVo.setCode(200);
        return resultVo;
    }

    public static ResultVo error(Integer code,String msg) {
        ResultVo resultVo = new ResultVo();

        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }
}
