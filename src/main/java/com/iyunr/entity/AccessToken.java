package com.iyunr.entity;

/**
 * @Description: TODO 微信凭证实体类  
 * @author Administrator  
 * @date 2017年11月3日
 */
public class AccessToken {
	private int id;
	private String accessToken;//凭证
	private String expiresIn;//凭证有效时间，单位：秒
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	@Override
	public String toString() {
		return "AccessToken [id=" + id + ", accessToken=" + accessToken
				+ ", expiresIn=" + expiresIn + "]";
	}
	
	
	
}
