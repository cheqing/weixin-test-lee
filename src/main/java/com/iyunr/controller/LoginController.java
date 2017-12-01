package com.iyunr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iyunr.entity.Login;
import com.iyunr.resultJson.ResultJson;

/**
 * @Description: TODO 登录  
 * @author Administrator  
 * @date 2017年11月30日
 */
@RestController
public class LoginController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ResultJson login(Login login){
		System.out.println(login);
//		System.out.println(email+"--"+password);
		return new ResultJson().success(login, "SUCCESS");
	}
}
