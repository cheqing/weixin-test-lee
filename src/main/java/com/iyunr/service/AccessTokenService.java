package com.iyunr.service;

import com.iyunr.entity.AccessToken;

/**
 * @Description: TODO 微信accesstoken凭证接口类  
 * @author Administrator  
 * @date 2017年11月15日
 */
public interface AccessTokenService {
	/**
	 * @Description: TODO 将accessToken插入到数据库 
	 * @Title: insertAccessToken 
	 * @param @param accessToken
	 * @param @return    参数  
	 * @return int    返回类型  
	 * @throws
	 */
	public int insertAccessToken(AccessToken accessToken);
	
	/**
	 * @Description: TODO 查询最新一个token
	 * @Title: queryLastToken 
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String queryLastToken();
}
