package com.iyunr.service;

/**
 * @Description: TODO 菜单service
 * @author Administrator  
 * @date 2017年11月15日
 */
public interface MenuService {
	/**
	 * @Description: TODO 创建菜单项 
	 * @Title: createMenu 
	 * @param @param menuJson
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String createMenu(String menuJson);
	
	/**
	 * @Description: TODO 查询菜单项 
	 * @Title: queryMenu 
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String queryMenu();
}
