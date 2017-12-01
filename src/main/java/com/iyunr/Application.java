package com.iyunr;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.iyunr.mapper")	//用于配置扫描mybatis的接口类扫描包
@ServletComponentScan	//设置启动时spring能够扫描到我们自己编写的servlet和filter,用于扫描Druid监控,否则自己编写的filter不会运行的	
public class Application extends SpringBootServletInitializer{
	private final static Logger logger = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.info("---------- SpringBoot info started -----------");
	}
}