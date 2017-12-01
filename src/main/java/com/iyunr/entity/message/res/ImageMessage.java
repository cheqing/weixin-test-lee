package com.iyunr.entity.message.res;

/**
 * @Description: TODO 图片消息  
 * <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[image]]></MsgType>
<Image>
<MediaId><![CDATA[media_id]]></MediaId>
</Image>
</xml>
 * @author Administrator  
 * @date 2017年11月8日
 */
public class ImageMessage extends BaseMessage{
	public Image Image = new Image();

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		this.Image = image;
	}
}
