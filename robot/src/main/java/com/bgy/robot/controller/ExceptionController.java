package com.bgy.robot.controller;

import com.bgy.robot.utils.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 适用于所有使用@ RequestMapping方法，并处理所有@ RequestMapping标注方法出现异常的统一处理。
 */
@ControllerAdvice
@ResponseBody
public class ExceptionController extends BaseController {

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e) {
        return renderError("通用异常：" + e.getMessage());
    }
}
