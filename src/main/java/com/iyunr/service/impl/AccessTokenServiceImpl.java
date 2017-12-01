package com.iyunr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iyunr.entity.AccessToken;
import com.iyunr.exceptionHandler.ServiceException;
import com.iyunr.mapper.AccessTokenMapper;
import com.iyunr.service.AccessTokenService;

/**
 * @Description: TODO accessToken处理实现类  
 * @author Administrator  
 * @date 2017年11月15日
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

	@Autowired
	private AccessTokenMapper accessTokenMapper;
	
	/**
	 * @Description: TODO 将获取到的tokne保存入库 
	 * @Title: insertAccessToken 
	 * @param @param accessToken 
	 * @param @return    参数  
	 * @return int    返回类型  
	 * @throws
	 */
	@Transactional
	public int insertAccessToken(AccessToken accessToken){
		int insetKey = accessTokenMapper.insertAccessToken(accessToken);
		return insetKey;
	}
	
	/**
	 * @Description: TODO 从库中查询最新的一个token 
	 * @Title: queryLastToken 
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String queryLastToken(){
		String token = accessTokenMapper.queryLastToken();
		if(token!=null && !"".equals(token)){
			return token;
		}
		throw new ServiceException("token无效或获取失败！");
	}

}
