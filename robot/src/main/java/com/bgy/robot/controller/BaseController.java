package com.bgy.robot.controller;

import com.bgy.robot.utils.JsonResult;

public class BaseController {

    /**
     * 渲染失败数据
     *
     * @return
     */
    protected JsonResult renderError() {
        JsonResult result = new JsonResult();
        result.setSuccess(false);
        result.setStatus("500");
        return result;
    }

    /**
     * 渲染失败数据（带信息）
     *
     * @param msg
     * @return
     */
    protected JsonResult renderError(String msg) {
        JsonResult result = renderError();
        result.setMsg(msg);
        return result;
    }

    /**
     * 渲染成功数据
     *
     * @return
     */
    protected JsonResult renderSuccess() {
        JsonResult result = new JsonResult();
        result.setSuccess(true);
        result.setStatus("200");
        return result;
    }

    /**
     * 渲染成功数据（带信息）
     *
     * @param msg
     * @return
     */
    protected JsonResult renderSuccess(String msg) {
        JsonResult result = renderSuccess();
        result.setMsg(msg);
        return result;
    }

    /**
     * 渲染成功数据（带数据）
     *
     * @param object
     * @return
     */
    protected JsonResult renderSuccess(Object object) {
        JsonResult result = renderSuccess();
        result.setObj(object);
        return result;
    }
}
