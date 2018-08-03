package com.example.cyui.demo.service.impl;

import com.example.cyui.demo.dao.register.RegisterDao;
import com.example.cyui.demo.entiy.Register;
import com.example.cyui.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class selectRegisterByRegisterImpl implements RegisterService {
    @Autowired
    RegisterDao registerDao;

    @Override
    public List<Register> selectRegisterByRegister(Register register) {
        return registerDao.selectRegisterByRegister(register);
    }
}
