package com.iyunr.entity.message.res;

/**
 *@Description: TODO 文本消息  
 *<xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[你好]]></Content>
</xml>
 * @author Administrator  
 * @date 2017年11月8日
 */
public class TextMessage extends BaseMessage{
	private String Content;	//文本消息内容
	private Long MsgId;	//消息id，64位整型
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Long getMsgId() {
		return MsgId;
	}

	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}

	@Override
	public String toString() {
		return "Message_text [Content=" + Content + ", MsgId=" + MsgId + "]";
	}
	
	
}
