package com.iyunr.entity;

import java.util.Arrays;

/**
 * @Description: TODO 用于封装获取用户列表信息的实体类,主要用于获取用户的open以及总关注数
 * @author Administrator  
 * @date 2017年11月7日
 */
public class User_info {
	private int total;	//关注该公众账号的总用户数
	private int count;	//拉取的OPENID个数，最大值为10000
	private OpenIdList data;	//列表数据，OPENID的列表
	private String next_openid;	//拉取列表的最后一个用户的OPENID
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public OpenIdList getData() {
		return data;
	}
	public void setData(OpenIdList data) {
		this.data = data;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	@Override
	public String toString() {
		return "UserInfo [total=" + total + ", count=" + count + ", data="
				+ data + ", next_openid=" + next_openid + "]";
	}
	
	
}

class OpenIdList{
	private String[] openid;	//用户openID

	public String[] getOpenid() {
		return openid;
	}

	public void setOpenid(String[] openid) {
		this.openid = openid;
	}

	@Override
	public String toString() {
		return "OpenIdList [openid=" + Arrays.toString(openid) + "]";
	}
	
	
}
