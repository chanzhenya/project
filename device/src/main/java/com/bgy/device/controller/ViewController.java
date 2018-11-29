package com.bgy.device.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/device")
    public String device() {
        return "/device";
    }

    @GetMapping("/device-type")
    public String deviceType() {
        return "/device-type";
    }

    @GetMapping("/branch")
    public String branch() {
        return "/branch";
    }

    @GetMapping("/attribute-type")
    public String attributeType() {
        return "/attribute-type";
    }

    @GetMapping("/attribute")
    public String attribute() {
        return "/attribute";
    }
}
