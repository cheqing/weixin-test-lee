package com.iyunr.entity.message.req;

/**
 * @Description: TODO 图片消息  
 * <xml>
 <ToUserName><![CDATA[toUser]]></ToUserName>
 <FromUserName><![CDATA[fromUser]]></FromUserName>
 <CreateTime>1348831860</CreateTime>
 <MsgType><![CDATA[image]]></MsgType>
 <PicUrl><![CDATA[this is a url]]></PicUrl>
 <MediaId><![CDATA[media_id]]></MediaId>
 <MsgId>1234567890123456</MsgId>
 </xml>
 * @author Administrator  
 * @date 2017年11月8日
 */
public class ImageMessage extends BaseMessage{
	private String PicUrl;	//图片链接（由系统生成）
	private String MediaId;	//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	
}
