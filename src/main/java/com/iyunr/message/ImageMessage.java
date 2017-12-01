package com.iyunr.message;

/**
 * @Description: TODO 图片消息  
 * @author Administrator  
 * @date 2017年11月3日
 */
public class ImageMessage extends BaseMessage{
	private String MediaId;	//通过素材管理中的接口上传多媒体文件，得到的id。

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	
}
