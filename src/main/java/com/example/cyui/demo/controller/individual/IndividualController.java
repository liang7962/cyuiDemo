package com.example.cyui.demo.controller.individual;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndividualController {

    @RequestMapping("/individualInfo.do")
    public String getIndividual(){
        return "individual/individualInfo";
    }

}
