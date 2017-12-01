package com.iyunr.message;

/**
 * @Description: TODO 封装与微信交互消息的基础类  
 * @author Administrator  
 * @date 2017年11月3日
 */
public class BaseMessage {
	private String ToUserName;	//开发者微信号
	private String FromUserName;	//开发者微信号
	private Long CreateTime;	//消息创建时间 （整型）
	private String MsgType;	//text
	private Long MsgId;	//消息id，64位整型
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public Long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public Long getMsgId() {
		return MsgId;
	}
	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}
	
	
}
