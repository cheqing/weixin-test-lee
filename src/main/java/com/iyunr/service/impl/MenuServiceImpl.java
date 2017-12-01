package com.iyunr.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iyunr.config.AccessTokenSchedulingConfig;
import com.iyunr.service.MenuService;
import com.iyunr.util.HttpUtil;

/**
 * @Description: TODO 菜单实现类  
 * @author Administrator  
 * @date 2017年11月15日
 */
@Service
public class MenuServiceImpl implements MenuService{
	@Transactional
	public String createMenu(String menuJson){
		String access_token = AccessTokenSchedulingConfig.ACCESSTOKEN;
		String data = HttpUtil.sendHttpReq("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token, "POST", menuJson, "utf-8");
		return data;
	}
	
	/**
	 * @Description: TODO 查询微信菜单 
	 * @Title: queryMenu 
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String queryMenu(){
		String access_token = AccessTokenSchedulingConfig.ACCESSTOKEN;
		String data = HttpUtil.sendHttpReq("https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+access_token, "GET", null, "utf-8");
		return data;
	}
}
