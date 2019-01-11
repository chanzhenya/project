package com.bgy.device.controller;

import com.bgy.device.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    public Model initParams(Model model) {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        SysUser user = new SysUser();
        user.setUsername(sysUser.getUsername());
        model.addAttribute("user",user);
        return model;
    }
}
