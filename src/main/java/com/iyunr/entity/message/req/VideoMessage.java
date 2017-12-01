package com.iyunr.entity.message.req;

/**
 * @Description: TODO 视频消息  (视频和小视频均可以使用这个，只是MsgType不一样，一个是video，一个是shortvideo)
 * <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>1357290913</CreateTime>
<MsgType><![CDATA[video]]></MsgType>
<MediaId><![CDATA[media_id]]></MediaId>
<ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
<MsgId>1234567890123456</MsgId>
</xml>
 * @author Administrator  
 * @date 2017年11月8日
 */
public class VideoMessage extends BaseMessage{
	private String MediaId;	//视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	private String ThumbMediaId;	//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	private Long MsgId;	//消息id，64位整型
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	public Long getMsgId() {
		return MsgId;
	}
	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}
	
	
}
