package com.example.cyui.demo.util;

import java.util.Random;

public class numberRandomUtil {

    public static String getRandomNumber(){
        Random random=new Random();
        Integer number=random.nextInt()*1000;
        return number.toString();
    }
}
