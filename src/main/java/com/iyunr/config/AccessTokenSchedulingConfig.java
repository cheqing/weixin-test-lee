package com.iyunr.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iyunr.entity.AccessToken;
import com.iyunr.exceptionHandler.ServiceException;
import com.iyunr.service.AccessTokenService;
import com.iyunr.util.HttpUtil;

/**
 * @Description: TODO 用于定时获取token的定时任务  
 * @author Administrator  
 * @date 2017年11月3日
 */
@Configuration
@EnableScheduling
public class AccessTokenSchedulingConfig {
	private final Logger logger = LoggerFactory.getLogger(AccessTokenSchedulingConfig.class);
	public static String ACCESSTOKEN = null;
	@Autowired
	private AccessTokenService accessTokenService;
	/**
	 * token 微信服务器是没7200秒（两小时）过期，我们这里每7000秒获取一次新的token
	 * @Description: TODO 
	 * @Title: accessTokenscheduler 
	 * @param     参数  
	 * @return void    返回类型  
	 * @throws
	 */
	@Scheduled(fixedDelay=7000*1000)
	public void accessTokenscheduler(){
		String appID = "wxbefffc2aefc4f25c";
		String appsecret = "8586a45b15166c8ced0289d71fa1ac8c";
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appID+"&secret="+appsecret;
		String accessToken = HttpUtil.sendHttpReq(url, "GET", null, "utf-8");
		JSONObject json = JSON.parseObject(accessToken);
		AccessToken token = new AccessToken();
		if(json.getString("errcode")!= null && "40013".equals(json.getString("errcode"))){//发生错误
			logger.error("获取token失败！", json.getString("errcode"), json.getString("errcode"));
			throw new ServiceException("获取token失败！--"+json.toJSONString());
		}else{
			logger.info("最新获取的AccessToken是："+accessToken);
			token.setAccessToken(json.getString("access_token"));
			token.setExpiresIn(json.getString("expires_in"));
			ACCESSTOKEN = json.getString("access_token");
			//将token存到数据库中
			int insertKey = accessTokenService.insertAccessToken(token);
			if(insertKey>0){
				logger.info("token写入数据库成功，token="+accessToken);
			}else{
				logger.error("token写入数据库失败！");
			}
		}
	}
}
