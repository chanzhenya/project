package com.bgy.device.utils;

import com.bgy.device.value.ResultVo;

public class ResultUtil {

    public static ResultVo success(Object object) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(200);
        resultVo.setMsg("success");
        resultVo.setData(object);
        return resultVo;
    }

    public static ResultVo error(String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(500);
        resultVo.setMsg(msg);
        return resultVo;
    }
}
