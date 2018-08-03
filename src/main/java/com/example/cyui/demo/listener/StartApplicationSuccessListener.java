package com.example.cyui.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: jinliang
 * @create: 2018/3/29 14:24
 * @desc:路由器监听
 * @param
 **/
@Component
public class StartApplicationSuccessListener implements ApplicationListener<ApplicationReadyEvent> {

    private static Logger logger= LoggerFactory.getLogger(StartApplicationSuccessListener.class);

    private final static String SLASH = "/";
    private final static String COLON = ":";

    @Value("${current.project.path}")
    private String path;
    @Value("${server.port}")
    private String port;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        logger.info("************************************************************************");
        logger.info("************************  PATH   "+path+COLON+port+contextPath+SLASH+"**********************");
        logger.info("************************************************************************");
    }
}
