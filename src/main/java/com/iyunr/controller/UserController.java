package com.iyunr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.iyunr.config.AccessTokenSchedulingConfig;
import com.iyunr.entity.Error_message;
import com.iyunr.entity.User;
import com.iyunr.entity.User_info;
import com.iyunr.resultJson.ResultJson;
import com.iyunr.service.UserService;

/**
 * @Description: TODO 微信用户controller  
 * @author Administrator  
 * @date 2017年11月7日
 */
@RestController
@RequestMapping(value="/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private UserService userService;
	
	/**
	 * @Description: TODO 批量获取用户列表 
	 * @Title: batchquery 
	 * @param @return    参数  
	 * @return ResultJson    返回类型  
	 * @throws
	 */
	@RequestMapping(value="/batchget", method=RequestMethod.POST)
	public ResultJson batchquery(){
		String data = userService.batchquery(AccessTokenSchedulingConfig.ACCESSTOKEN);
		Error_message resultjson = JSON.parseObject(data, Error_message.class);
		if(resultjson.getErrcode() == null){
			return new ResultJson().success(data, "SUCCESS");
		}
		logger.error("批量微信用户列表查询失败！", data);
		return new ResultJson().failure("批量微信用户列表查询失败！--"+data);
	}
	
	/**
	 * @Description: TODO 获取用户列表 
	 * @Title: queryUsers 
	 * @param @return    参数  
	 * @return ResultJson    返回类型  
	 * @throws
	 */
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public ResultJson queryUsers(){
		String data = userService.queryUsers(AccessTokenSchedulingConfig.ACCESSTOKEN);
		Error_message resultjson = JSON.parseObject(data, Error_message.class);
		if(resultjson.getErrcode() == null){
			return new ResultJson().success(JSON.parseObject(data, User_info.class), "SUCCESS");
		}
		logger.error("微信用户列表查询失败！", data);
		return new ResultJson().failure("微信用户列表查询失败！--"+data);
	}
	
	/**
	 * @Description: TODO 根据openId获取用户信息 
	 * @Title: queryUserInfo 
	 * @param @return    参数  
	 * @return ResultJson    返回类型  
	 * @throws
	 */
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public User queryUserInfoByOpenId(String openId){
		User user = userService.queryWx_user(AccessTokenSchedulingConfig.ACCESSTOKEN, openId, "zh_CN");
		if(user != null){
			return user;
		}
		return null;
//		if(user != null){
//			return new ResultJson().success(user, "SUCCESS");
//		}
//		logger.error("查询用户信息失败！");
//		return new ResultJson().failure("查询用户信息失败！");
	}
}
