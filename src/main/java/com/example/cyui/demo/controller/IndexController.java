package com.example.cyui.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class IndexController {



    @RequestMapping("/index.html")
    public String toIndex(){

        return "index";
    }


    @RequestMapping("/main.html")
    public String getMain(ModelAndView modelAndView){

        return "main";
    }


/*    public void aa(){
        //函数式编程方式
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        long countBigThan3 = list.stream()
                .filter(value -> value > 3)
                .count();

        List<String> lowerList = Stream.of("a","b","c")
                .collect(Collectors.toList());

    }*/
    public void ceshi(HttpServletRequest request, HttpServletResponse response){
        //获取get请求name的值
        request.getQueryString();
        //获取post请求name的值
        request.getParameter("aa");
    }
}
