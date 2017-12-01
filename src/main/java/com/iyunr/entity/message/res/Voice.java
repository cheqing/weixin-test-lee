package com.iyunr.entity.message.res;

/**
 * @Description: TODO 语音消息实体  
 * @author Administrator  
 * @date 2017年11月10日
 */
public class Voice {
	private String MediaId;	//通过素材管理中的接口上传多媒体文件，得到的id

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
}
