package com.iyunr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.iyunr.entity.Error_message;
import com.iyunr.entity.Menu_commonButton;
import com.iyunr.entity.Menu_complexButton;
import com.iyunr.entity.Menu_list;
import com.iyunr.resultJson.ResultJson;
import com.iyunr.service.MenuService;

//@Controller
@RestController
@RequestMapping(value="/menu")
public class MenuController {
	private final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResultJson createMenu(){
		String menuJson = this.menuJson();
		String data = menuService.createMenu(menuJson);
		Error_message resultjson = JSON.parseObject(data, Error_message.class);
		if("0".equals(resultjson.getErrcode())){
			return new ResultJson().success(data, "CREATED");
		}
		logger.error("创建微信菜单失败！");
		return new ResultJson().failure("创建微信菜单失败！");
	}
	
	public String menuJson(){
		Menu_commonButton button1 = new Menu_commonButton();
		button1.setType("scancode_push");
		button1.setName("扫码推事件a");
		button1.setKey("selfmenu_1_1");
		Menu_commonButton button2 = new Menu_commonButton();
		button2.setType("scancode_waitmsg");
		button2.setName("扫码带提示a");
		button2.setKey("selfmenu_1_2");
		Menu_commonButton button3 = new Menu_commonButton();
		button3.setType("view");
		button3.setName("iworld");
		button3.setUrl("http://iworld.55555.io/");
		
		Menu_complexButton sub1 = new Menu_complexButton();
		sub1.setName("扫码a");
		sub1.setSub_button(new Menu_commonButton[]{button1, button2, button3});
		
		Menu_commonButton button4 = new Menu_commonButton();
		button4.setType("location_select");
		button4.setName("发送位置a");
		button4.setKey("rselfmenu_2_0");
		
		Menu_complexButton sub2 = new Menu_complexButton();
		sub2.setName("菜单a");
		sub2.setSub_button(new Menu_commonButton[]{button4});
		
		Menu_list menu = new Menu_list();
		menu.setButton(new Menu_complexButton[]{sub1, sub2});
		
		String menuJson = JSON.toJSONString(menu);
		return menuJson;
	}
	
	/**
	 * @Description: TODO 微信菜单列表查询 
	 * @Title: queryMenu 
	 * @param @return    参数  
	 * @return ResultJson    返回类型  
	 * @throws
	 */
	@RequestMapping(value="/get")
	public ResultJson queryMenu(){
		String menuJson = menuService.queryMenu();
		Error_message resultjson = JSON.parseObject(menuJson, Error_message.class);
		if(resultjson.getErrcode() == null){
			return new ResultJson().success(menuJson, "SUCCESS");
		}
		logger.error("微信菜单列表查询失败！");
		return new ResultJson().failure("微信菜单列表查询失败！");
	}
}
