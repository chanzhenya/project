package com.bgy.robot.accesscontrol.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.bgy.robot.accesscontrol.entity.Account;
import com.bgy.robot.accesscontrol.service.AccountService;
import com.bgy.robot.accesscontrol.web.utils.ResultDataUtil;
import com.bgy.robot.accesscontrol.web.value.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Controller
public class RegisterController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "/submit")
    @ResponseBody
    public ResultData register(@RequestParam("photo")MultipartFile photo, @RequestParam("data")String data) {

        try {

            JSONObject params = JSONObject.parseObject(data);

            String name = params.getString("name");
            Integer gender = params.getInteger("gender");
            String jobNumber = params.getString("jobNumber");
            String title = params.getString("title");


            //用户信息存储
            Account account = new Account();
            account.setName(name);
            account.setGender(gender);
            account.setJobNo(jobNumber);
            account.setTitle(title);
            accountService.saveAccount(account,photo);

            return ResultDataUtil.success(null,null);
        } catch (Exception e) {
            return ResultDataUtil.error("上传文件大小不得超过10MB");
        }
    }
}
