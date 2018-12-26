package com.bgy.device.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping({"/","/device"})
    public String device() {
        return "device";
    }

    @GetMapping("/device-type")
    public String deviceType() {
        return "device-type";
    }

    @GetMapping("/table")
    public String table(){
        return "table";
    }

    @GetMapping("/dish")
    public String dish() {
        return "dish";
    }

    @GetMapping("/device-start")
    public String deviceStart() {
        return "device-start";
    }
}
