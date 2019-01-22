package com.bgy.device.exception;

import com.bgy.device.entity.BaseException;
import com.bgy.device.utils.ResultUtil;
import com.bgy.device.value.ResultVo;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Logger;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultVo handleException(Exception e) {
        return ResultUtil.error("通用异常：" + e.getMessage());
    }

    /**
     * 操作数据库出现异常:名称重复，外键关联
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResultVo handleException(DataIntegrityViolationException e) {
        log.error(e.getMessage());
        ResultVo resultVo = ResultUtil.error("操作数据库出现异常：字段重复、有外键关联等");
        return resultVo;
    }

    @ExceptionHandler(value = MySQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVo handleMySQLIntegrityConstraintViolationException(Exception e) {
        return ResultUtil.error("主键值或唯一键重复："+e.getMessage());
    }
    
    /**
     * 自定义异常
     */
    @ExceptionHandler(BaseException.class)
    public ResultVo handleException(BaseException e) {
        ResultVo resultVo = ResultUtil.error(e.getMsg());
        return resultVo;
    }
}
