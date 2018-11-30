package com.bgy.device.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bgy.device.entity.Branch;
import com.bgy.device.service.BranchService;
import com.bgy.device.utils.ResultUtil;
import com.bgy.device.value.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping("/list")
    public ResultVo list() {
        try {
            List<Branch> branches = branchService.findAll();
            return ResultUtil.success(branches);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    @PostMapping("/save")
    public ResultVo save(@RequestBody Map<String,Object> params) {
        try {
            Branch branch = new Branch();
            branch.setBranchId((Integer) params.get("branchId"));
            branch.setBranchName((String) params.get("branchName"));
            branch.setBranchNo((String) params.get("branchNo"));
            branchService.save(branch);
            return ResultUtil.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResultVo delete(@RequestBody Map<String,Object> params) throws Exception {
        String data = (String) params.get("data");
        JSONArray array = JSONArray.parseArray(data);
        List<Branch> branches = new ArrayList<>();
        for(int i=0;i<array.size();i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            Branch branch = new Branch();
            branch.setBranchId(jsonObject.getInteger("branchId"));
            branch.setBranchName(jsonObject.getString("branchName"));
            branch.setBranchNo(jsonObject.getString("branchNo"));
            branches.add(branch);
        }
        branchService.delete(branches);
        return ResultUtil.success(null);
    }
}
