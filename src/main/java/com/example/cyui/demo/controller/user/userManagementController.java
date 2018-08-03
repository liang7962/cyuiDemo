package com.example.cyui.demo.controller.user;

import com.example.cyui.demo.dto.Register.RegisterDto;
import com.example.cyui.demo.entiy.Register;
import com.example.cyui.demo.framework.page.Page;
import com.example.cyui.demo.service.RegisterService;
import com.example.cyui.demo.util.BeanCopyUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class userManagementController {

    private Logger logger= LoggerFactory.getLogger(userManagementController.class);

    @Autowired
    RegisterService registerService;

    @RequestMapping(value = "/userManagement")
    public String goUserManagement(ModelMap modelMap, RegisterDto dto, Page page){
        List<RegisterDto> registerDtoList=null;
        Register register= BeanCopyUtil.copyTo(dto,new Register());
        //分页处理
        com.github.pagehelper.Page<Object> pageInfo = PageHelper.startPage(page.getPageNum(), page.getNumPerPage()).setOrderBy(page.getOrderBy());
        List<Register> registerList = registerService.selectRegisterByRegister(register);
        page.setTotalCount(pageInfo.getTotal());

        registerDtoList= BeanCopyUtil.copyTo(registerList,RegisterDto.class);
        modelMap.put("dto",dto);
        modelMap.put("registerDtoList",registerDtoList);
        logger.info("registerDtoList>>>>>>>>>>>>>>>>"+registerDtoList.toString());
        return "user/userManagement";
    }

}
