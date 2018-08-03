package com.example.cyui.demo.service;

import com.example.cyui.demo.entiy.Register;

import java.util.List;

public interface RegisterService {
    public List<Register> selectRegisterByRegister(Register register);
}
