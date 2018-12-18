package com.bgy.device.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bgy.device.service.TableService;
import com.bgy.device.utils.ResultUtil;
import com.bgy.device.value.ResultVo;
import com.bgy.device.value.TableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Judith
 * @date 2018/12/14
 */
@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;

    @PostMapping("/list")
    public ResultVo list() {
        List<TableVo> tables = tableService.findAll();
        return ResultUtil.success(tables);
    }

    @PostMapping("/save")
    public ResultVo save(@RequestBody Map<String,Object> params) {
        TableVo tableVo = initParam(params);
        tableService.updateTable(tableVo);
        return ResultUtil.success(null);
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestBody Map<String,Object> params) {
        String data = (String) params.get("data");
        JSONArray jsonArray = JSONArray.parseArray(data);
        List<TableVo> tableVos = new ArrayList<>();
        for(int i=0;i<jsonArray.size();i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            tableVos.add(initParam(jsonObject));
        }
        tableService.delete(tableVos);
        return ResultUtil.success(null);
    }

    private TableVo initParam(Map<String,Object> params) {
        TableVo tableVo = new TableVo();
        tableVo.setTableId((String) params.get("tableId"));
        tableVo.setTableName((String) params.get("tableName"));
        tableVo.setPositionCode((String) params.get("positionCode"));
        tableVo.setDeviceId((String) params.get("deviceId"));
        return tableVo;
    }
}
