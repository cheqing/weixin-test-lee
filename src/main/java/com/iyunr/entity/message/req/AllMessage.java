package com.iyunr.entity.message.req;

/**
 * @Description: TODO 用于封装所有消息属性的实体类  
 * @author Administrator  
 * @date 2017年11月8日
 */
public class AllMessage {
	private String ToUserName;	//开发者微信号(公共)
	private String FromUserName;	//发送方帐号（一个OpenID）(公共)
	private Long CreateTime;	//消息创建时间 （整型）(公共)
	private String MsgType;	//text(公共)
	private String Content;	//文本消息内容（文本）
	private String PicUrl;	//图片链接（由系统生成）（图片）
	private String MediaId;	//消息媒体id，可以调用多媒体文件下载接口拉取数据。（图片、语音、）
	private String Format;	//语音格式，如amr，speex等（语音）
	private String Recognition;	//语音识别结果，UTF8编码（语音）
	private String ThumbMediaId;	//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。（视频）
	private String Location_X;	//地理位置维度（地理位置）
	private String Location_Y;	//地理位置经度（地理位置）
	private String Scale;	//地图缩放大小（地理位置）
	private String Label;	//地理位置信息（地理位置）
	private String Title;	//消息标题（链接）
	private String Description;	//消息描述（链接）
	private String Url;	//消息链接（链接）
	private Long MsgId;	//消息id，64位整型（文本、图片、语音、视频、地理位置、链接）
	private String Event;	//事件类型，subscribe(订阅)、unsubscribe(取消订阅)；二维码：未关注（subscribe），已关注（SCAN）；地理位置：LOCATION；点击：CLICK;跳转：view等
	private String EventKey;	//事件KEY值，未关注（qrscene_为前缀，后面为二维码的参数值），已关注（是一个32位无符号整数，即创建二维码时的二维码scene_id）
	private String Ticket;	//二维码的ticket，可用来换取二维码图片
	private String Latitude;	//事件-地理位置纬度
	private String Longitude;	//事件-地理位置经度
	private String Precision;	//事件-地理位置精度
	private String MenuId;	//菜单id
	
	public String getMenuId() {
		return MenuId;
	}
	public void setMenuId(String menuId) {
		MenuId = menuId;
	}
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public Long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
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
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getRecognition() {
		return Recognition;
	}
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
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
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public Long getMsgId() {
		return MsgId;
	}
	public void setMsgId(Long msgId) {
		MsgId = msgId;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	@Override
	public String toString() {
		return "Message_all [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
				+ MsgType + ", Content=" + Content + ", PicUrl=" + PicUrl
				+ ", MediaId=" + MediaId + ", Format=" + Format
				+ ", Recognition=" + Recognition + ", ThumbMediaId="
				+ ThumbMediaId + ", Location_X=" + Location_X + ", Location_Y="
				+ Location_Y + ", Scale=" + Scale + ", Label=" + Label
				+ ", Title=" + Title + ", Description=" + Description
				+ ", Url=" + Url + ", MsgId=" + MsgId + ", Event=" + Event
				+ ", EventKey=" + EventKey + ", Ticket=" + Ticket
				+ ", Latitude=" + Latitude + ", Longitude=" + Longitude
				+ ", Precision=" + Precision + "]";
	}
	
	
}
