package com.iyunr.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO 用于对应application.properties文件中的微信常用变量  
 * @author Administrator  
 * @date 2017年11月16日
 */
@Component
@ConfigurationProperties(prefix = "weixin")
//PropertySource默认取application.properties 
// @PropertySource(value = "config.properties")
public class WeixinCommonParam {
	public String appid;	//公众号的唯一标识
	public String appsecret;	//公众号的appsecret
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	
	
}
