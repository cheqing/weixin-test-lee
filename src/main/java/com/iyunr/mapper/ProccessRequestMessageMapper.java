package com.iyunr.mapper;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: TODO 用于处理用户下次请求  
 * @author Administrator  
 * @date 2017年11月9日
 */
public interface ProccessRequestMessageMapper {
	/**
	 * @Description: TODO 处理用户消息，最终封装成xml返回 
	 * @Title: proccessMessage 
	 * @param @param request
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String proccessMessage(HttpServletRequest request);
}
