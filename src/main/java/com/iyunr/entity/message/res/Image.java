package com.iyunr.entity.message.res;

/**
 * @Description: TODO 图片消息  
 * @author Administrator  
 * @date 2017年11月9日
 */
public class Image {
	public String MediaId;	//图片消息媒体id，可以调用多媒体文件下载接口拉取数据。

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
