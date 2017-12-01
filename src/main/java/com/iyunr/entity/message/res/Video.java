package com.iyunr.entity.message.res;

/**
 * @Description: TODO 视频消息实体  
 * @author Administrator  
 * @date 2017年11月10日
 */
public class Video {
	private String MediaId;	//通过素材管理中的接口上传多媒体文件，得到的id
	private String Title;	//视频消息的标题
	private String Description;	//Description
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}
