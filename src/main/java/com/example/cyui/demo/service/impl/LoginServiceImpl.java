package com.example.cyui.demo.service.impl;

import com.example.cyui.demo.dao.Individual.LoginDao;
import com.example.cyui.demo.entiy.Individual;
import com.example.cyui.demo.service.LoginService;
import com.example.cyui.demo.util.MD5Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDao loginDao;

    Logger logger= LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public Individual selectIndividualByIndividualDto(Individual individual) {

        String password=individual.getUserPassword();
        individual.setUserPassword(MD5Util.md5Encode(password));
        logger.info("加密后的密码："+MD5Util.md5Encode(password));
        return loginDao.selectIndividualByIndividual(individual);

    }
}
