package com.bgy.robot.accesscontrol.web.controller;

import com.bgy.robot.accesscontrol.web.utils.ResultDataUtil;
import com.bgy.robot.accesscontrol.web.value.ResultData;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

/**
 * 统一处理异常控制器
 */
@ControllerAdvice
@SuppressWarnings("unchecked")
public class ExcptionController {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultData exception(Exception ex) {
        ex.printStackTrace();
        if(ex instanceof MultipartException) {
            return ResultDataUtil.error("文件大小限制为10MB");
        }  else {
            return ResultDataUtil.error(ex.getMessage());
        }
    }
}
