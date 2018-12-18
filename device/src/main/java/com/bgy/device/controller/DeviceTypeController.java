package com.bgy.device.controller;

import com.bgy.device.entity.DeviceType;
import com.bgy.device.service.DeviceTypeService;
import com.bgy.device.utils.ResultUtil;
import com.bgy.device.value.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Judith
 * @date 2018/12/14
 */
@RestController
@RequestMapping("/device-type")
public class DeviceTypeController {

    @Autowired
    private DeviceTypeService deviceTypeService;

    @PostMapping("/list")
    public ResultVo list() {
        List<DeviceType> deviceTypes = deviceTypeService.findAll();
        return ResultUtil.success(deviceTypes);
    }
}
