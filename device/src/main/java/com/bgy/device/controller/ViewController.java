package com.bgy.device.controller;

import com.alibaba.fastjson.JSON;
import com.bgy.device.common.CommonCoreContent;
import com.bgy.device.entity.SysUser;
import com.bgy.device.redis.RedisService;
import com.bgy.device.redis.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController extends BaseController {

    @Autowired
    private RedisService redisService;

    @GetMapping({"/device","/"})
    public String device(Model model) {
        initParams(model);
        return "device";
    }

    @GetMapping("/device-type")
    public String deviceType(Model model) {
        initParams(model);
        return "device-type";
    }

    @GetMapping("/table")
    public String table(Model model){
        initParams(model);
        return "table";
    }

    @GetMapping("/dish")
    public String dish(Model model) {
        initParams(model);
        return "dish";
    }

    @GetMapping("/device-start")
    public String deviceStart(Model model) {
        initParams(model);
        return "device-start";
    }

    public Model initParams(Model model) {
        String token = getToken();
        String username = redisService.get(RedisKeyUtil.getTokenKey(token));
        model.addAttribute("username",username);
        return model;
    }
}
