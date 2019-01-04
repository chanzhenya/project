package com.bgy.robot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Judith
 * @date 2018/12/13
 */
@Controller
public class IndexController {

    @GetMapping({"/","/index","index.html"})
    public String index() {
        return "index";
    }
}
