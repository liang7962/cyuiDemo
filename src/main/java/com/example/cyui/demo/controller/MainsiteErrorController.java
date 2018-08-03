package com.example.cyui.demo.controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: jinliang
 * @create: 2018/4/27 16:23
 * @desc: 统一错误信息跳转error页面
 * @param null
 **/
@Controller
public class MainsiteErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value=ERROR_PATH)
    public String error(){
        return "404";
    }
}
