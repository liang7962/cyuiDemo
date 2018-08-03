package com.example.cyui.demo.dao.register;

import com.example.cyui.demo.entiy.Register;

import java.util.List;

public interface RegisterDao {
    List<Register> selectRegisterByRegister(Register register);
}
