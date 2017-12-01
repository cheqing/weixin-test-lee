package com.iyunr.entity.message.req;

/**
 * @Description: TODO  扫描带参数二维码事件消息
 * <xml><ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[FromUser]]></FromUserName>
<CreateTime>123456789</CreateTime>
<MsgType><![CDATA[event]]></MsgType>
<Event><![CDATA[subscribe]]></Event>
<EventKey><![CDATA[qrscene_123123]]></EventKey>
<Ticket><![CDATA[TICKET]]></Ticket>
</xml>
 * @author Administrator  
 * @date 2017年11月8日
 */
public class EventTicketMessage extends BaseMessage{
	private String Event;	//事件类型，未关注时（subscribe）， 关注时（SCAN）
	private String EventKey;	//事件类型，未关注时（qrscene_为前缀，后面为二维码的参数值）， 关注时（是一个32位无符号整数，即创建二维码时的二维码scene_id）
	private String Ticket;	//二维码的ticket，可用来换取二维码图片

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

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	
	
}
