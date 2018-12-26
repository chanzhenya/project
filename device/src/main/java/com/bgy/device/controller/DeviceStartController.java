package com.bgy.device.controller;

import com.bgy.device.common.CommonCoreContent;
import com.bgy.device.service.DeviceService;
import com.bgy.device.utils.ResultUtil;
import com.bgy.device.value.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Judith
 * @date 2018/12/20
 */
@RestController
@RequestMapping("/device-start")
@Slf4j
public class DeviceStartController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 执行脚本命令，控制设备的运行状态
     * @param cmdIndex
     * @return
     */
    @RequestMapping("/execute")
    public ResultVo execute(@RequestParam("cmdIndex") Integer cmdIndex) {
        String cmd = CommonCoreContent.cmdList.get(cmdIndex);
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
            String result = stringBuffer.toString();
            log.info("Result: "+result);
            return ResultUtil.success(null);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    @RequestMapping("/init")
    public ResultVo initDataBase() {
        deviceService.initDatabase();
        return ResultUtil.success(null);
    }
}
