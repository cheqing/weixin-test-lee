package com.iyunr.service;

import com.iyunr.entity.User;

public interface UserService {
	/**
	 * @Description: TODO 批量获取用户列表，最多支持一次拉去100条 
	 * @Title: batchquery 
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String batchquery(String accessToken);
	
	/**
	 * @Description: TODO 获取用户列表 
	 * 公众号可通过本接口来获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求。
	 * @Title: queryUser 
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String queryUsers(String accessToken);
	
	/**
	 * @Description: TODO 根据token以及用户openid获取用户信息 
	 * @Title: queryWx_user 
	 * @param @param accessToken 调用接口凭证
	 * @param @param openId 普通用户的标识，对当前公众号唯一
	 * @param @param lang 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 * @param @return    参数  
	 * @return Wx_user    返回类型  
	 * @throws
	 */
	public User queryWx_user(String accessToken, String openId, String lang);
	
	/**
	 * @Description: TODO 新增用户信息入库 
	 * @Title: insertUser 
	 * @param @param user
	 * @param @return    参数  
	 * @return int    返回类型  
	 * @throws
	 */
	public int insertUser(User user);
	
	/**
	 * @Description: TODO 将用户改成已取消关注用户
	 * @Title: uptUsertoUnSubscribe 
	 * @param @param openid
	 * @param @return    参数  
	 * @return int    返回类型  
	 * @throws
	 */
	public int uptUsertoUnSubscribe(String openid);
}
