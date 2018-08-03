package com.example.cyui.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrController {
    @RequestMapping("/404.html")
    public String toError(){
        return "404";
    }
}
