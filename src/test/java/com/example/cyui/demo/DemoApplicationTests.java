package com.example.cyui.demo;

import com.example.cyui.demo.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	Logger logger= LoggerFactory.getLogger(DemoApplicationTests.class);



	@Test
	public void contextLoads() {
		RedisUtils.set("we","www");
		String name= (String) RedisUtils.get("we");
		logger.info(name);
	}

}
