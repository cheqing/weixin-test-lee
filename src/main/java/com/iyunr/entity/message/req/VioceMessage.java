package com.iyunr.entity.message.req;

/**
 * @Description: TODO 语音消息  
 * <xml>
	<ToUserName><![CDATA[toUser]]></ToUserName>
	<FromUserName><![CDATA[fromUser]]></FromUserName>
	<CreateTime>1357290913</CreateTime>
	<MsgType><![CDATA[voice]]></MsgType>
	<MediaId><![CDATA[media_id]]></MediaId>
	<Format><![CDATA[Format]]></Format>
	<MsgId>1234567890123456</MsgId>
	</xml>
 * @author Administrator  
 * @date 2017年11月8日
 */
public class VioceMessage {
	private String MediaId;	//语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String Format;	//语音格式，如amr，speex等
	private Long MsgId;	//消息id，64位整型
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public Long getMsgId() {
		return MsgId;
	}
	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}
	
	
}
