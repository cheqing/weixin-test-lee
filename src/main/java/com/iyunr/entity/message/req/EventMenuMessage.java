package com.iyunr.entity.message.req;

/**
 * @Description: TODO  自定义菜单事件消息
 * <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[FromUser]]></FromUserName>
<CreateTime>123456789</CreateTime>
<MsgType><![CDATA[event]]></MsgType>
<Event><![CDATA[CLICK]]></Event>
<EventKey><![CDATA[EVENTKEY]]></EventKey>
</xml>
 * @author Administrator  
 * @date 2017年11月8日
 */
public class EventMenuMessage {
	private String Event;	//事件类型，拉取（CLICK） 跳转（VIEW）
	private String EventKey;	//事件KEY值，拉取（事件KEY值，与自定义菜单接口中KEY值对应） 跳转（设置的跳转URL）
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
	
}
