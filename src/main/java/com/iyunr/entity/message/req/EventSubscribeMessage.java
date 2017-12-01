package com.iyunr.entity.message.req;

/**
 * @Description: TODO  关注/取消关注事件消息
 * <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[FromUser]]></FromUserName>
<CreateTime>123456789</CreateTime>
<MsgType><![CDATA[event]]></MsgType>
<Event><![CDATA[subscribe]]></Event>
</xml>
 * @author Administrator  
 * @date 2017年11月8日
 */
public class EventSubscribeMessage extends BaseMessage{
	private String Event;	//事件类型，subscribe(订阅)、unsubscribe(取消订阅)

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}
	
}
