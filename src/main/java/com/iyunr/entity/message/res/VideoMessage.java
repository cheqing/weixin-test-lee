package com.iyunr.entity.message.res;

/**
 * @Description: TODO 视频消息  (视频和小视频均可以使用这个，只是MsgType不一样，一个是video，一个是shortvideo)
 * <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[video]]></MsgType>
<Video>
<MediaId><![CDATA[media_id]]></MediaId>
<Title><![CDATA[title]]></Title>
<Description><![CDATA[description]]></Description>
</Video> 
</xml>
 * @author Administrator  
 * @date 2017年11月8日
 */
public class VideoMessage extends BaseMessage{
	private Video Video = new Video();

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		this.Video = video;
	}
	
}
