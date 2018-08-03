package com.example.cyui.demo.dto.login;

import com.example.cyui.demo.entiy.Individual;

import java.io.Serializable;

/*
* 登录code，1：成功，其他代表失败*/
public class LoginMessage implements Serializable {
    private String code;
    private String msg;
    private String token;
    private Individual individual;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }
}
