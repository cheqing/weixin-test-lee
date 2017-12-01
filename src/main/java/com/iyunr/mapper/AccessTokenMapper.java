package com.iyunr.mapper;

import com.iyunr.entity.AccessToken;

/**
 * @Description: TODO 微信凭证mapper  
 * @author Administrator  
 * @date 2017年11月6日
 */
public interface AccessTokenMapper {
	/**
	 * @Description: TODO 将tokne插入到库中 
	 * @Title: insertAccessToken 
	 * @param @param accessToken
	 * @param @return    参数  
	 * @return int    返回类型  
	 * @throws
	 */
	public int insertAccessToken(AccessToken accessToken);
	
	/**
	 * @Description: TODO 获取最新的token
	 * @Title: queryLastToken 
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String queryLastToken();
}
