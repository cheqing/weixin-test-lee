package com.iyunr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.iyunr.entity.Error_message;
import com.iyunr.entity.User;
import com.iyunr.mapper.UserMapper;
import com.iyunr.service.UserService;
import com.iyunr.util.HttpUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * @Description: TODO 批量获取用户列表，最多支持一次拉去100条 
	 * @Title: batchquery 
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String batchquery(String accessToken){
		String data = HttpUtil.sendHttpReq("https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token="+accessToken, "POST", null, "utf-8");
		return data;
	}
	
	/**
	 * @Description: TODO 获取用户列表 
	 * 公众号可通过本接口来获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求。
	 * @Title: queryUser 
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public String queryUsers(String accessToken){
		String data = HttpUtil.sendHttpReq("https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken, "GET", null, "utf-8");
		return data;
	}
	
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
	public User queryWx_user(String accessToken, String openId, String lang){
		String data = HttpUtil.sendHttpReq("https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid="+openId+"&lang="+lang, "GET", null, "utf-8");
		Error_message resultjson = JSON.parseObject(data, Error_message.class);
		if(resultjson.getErrcode() == null){
			return JSON.parseObject(data, User.class);
		}
		return null;
	}
	
	/**
	 * @Description: TODO 新增用户信息入库 
	 * @Title: insertUser 
	 * @param @param user
	 * @param @return    参数  
	 * @return int    返回类型  
	 * @throws
	 */
	@Transactional
	public int insertUser(User user){
		int userId = userMapper.insertUser(user);
		return userId;
	}
	
	/**
	 * @Description: TODO 将用户改成已取消关注用户
	 * @Title: uptUsertoUnSubscribe 
	 * @param @param openid
	 * @param @return    参数  
	 * @return int    返回类型  
	 * @throws
	 */
	@Transactional(rollbackFor={Exception.class})
	@Override
	public int uptUsertoUnSubscribe(String openid){
		int uptNo = userMapper.uptUsertoUnSubscribe(openid);
//		int i = 1/0;
		return uptNo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Autowired
//	private UserMapper userMapper;
//	
//	public List<User> getAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public List<User> likeUserName(String name){
//		List<User> users = userMapper.likeUserName(name);
//		return users;
//	}
//
//	@Transactional
//	public int addUser(User user) {
//		int addNum = userMapper.addUser(user);
////		int aa = 3/0;//异常测试
//		return addNum;
//	}
//	
//	
//	@Transactional
//	public int uptUserById(User user){
//		int uptNum = userMapper.uptUserById(user);
//		return uptNum;
//	}
//	
//	@Transactional
//	public int delUserById(String id){
//		int delNum = userMapper.delUserById(id);
//		return delNum;
//	}
//	
//	public Map<String, Integer> testTran(User user, String id){
//		int addNum = this.addUser(user);
//		int delNum = this.delUserById(id);
//		Map<String, Integer> data = new HashMap<String, Integer>();
//		data.put("addNum", addNum);
//		data.put("delNum", delNum);
//		return data; 
//	}
}
