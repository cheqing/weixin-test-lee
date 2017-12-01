package com.iyunr.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iyunr.entity.message.req.AllMessage;
import com.iyunr.entity.message.res.Article;
import com.iyunr.entity.message.res.Image;
import com.iyunr.entity.message.res.ImageMessage;
import com.iyunr.entity.message.res.Music;
import com.iyunr.entity.message.res.MusicMessage;
import com.iyunr.entity.message.res.NewsMessage;
import com.iyunr.entity.message.res.TextMessage;
import com.iyunr.entity.message.res.Video;
import com.iyunr.entity.message.res.VideoMessage;
import com.iyunr.entity.message.res.Voice;
import com.iyunr.entity.message.res.VoiceMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * @Description: TODO  消息处理工具类，包括接收消息和发送消息的处理
 * @author Administrator  
 * @date 2017年11月8日
 */
public class MessageUtil {
	private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);
	public static final String REQ_MSG_TYPE_TEXT = "text";	//接收消息类型：文本
	public static final String REQ_MSG_TYPE_IMAGE = "image";	//接收消息类型：图片
	public static final String REQ_MSG_TYPE_VOICE = "voice";	//接收消息类型：语音
	public static final String REQ_MSG_TYPE_VIDEO = "video";	//接收消息类型：视频
	public static final String REQ_MSG_TYPE_SHORTVIDEO = "shortvideo";	//接收消息类型：小视频
	public static final String REQ_MSG_TYPE_LOCATION = "location";	//接收消息类型：文本
	public static final String REQ_MSG_TYPE_LINK = "link";	//接收消息类型：连接
	public static final String REQ_MSG_TYPE_EVENT = "event";	//接收消息类型：事件推送
	public static final String REQ_EVENT_TYPE_SUBSCRIBE = "subscribe";	//事件类型：未关注的用户进行关注后的事件推送(subscribe(订阅)and 未关注群体扫描二维码)
	public static final String REQ_EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";	//事件类型：用户取消订阅时
	public static final String REQ_EVENT_TYPE_SCAN = "scan";	//事件类型：用户已关注时的事件推送(已关注群体扫描二维码)
	public static final String REQ_EVENT_TYPE_LOCATION = "location";	//事件类型：地理位置事件类型
	public static final String REQ_EVENT_TYPE_CLICK = "click";	//事件类型：自定义事件类型，点击事件
	public static final String REQ_EVENT_TYPE_VIEW = "view";	//事件类型：自定义事件类型，菜单链接跳转事件
	public static final String EVENT_TYPE_TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";	//事件类型：transfer_customer_service(把消息推送给客服)
	public static final String RES_MSG_TYPE_TEXT = "text";	//响应消息类型：文本
	public static final String RES_MSG_TYPE_IMAGE = "image";	//响应消息类型：图片
	public static final String RES_MSG_TYPE_VOICE = "voice";	//响应消息类型：语音
	public static final String RES_MSG_TYPE_VIDEO = "video";	//响应消息类型：视频
	public static final String RES_MSG_TYPE_MUSIC = "music";	//响应消息类型：音乐
	public static final String RES_MSG_TYPE_NEWS = "news";	//响应消息类型：图文
	
//	/**
//     * 解析微信发来的请求（XML）
//     *
//     * @param request
//     * @return
//     * @throws Exception
//     */
//    @SuppressWarnings("unchecked")
//    //屏蔽某些编译时的警告信息(在强制类型转换的时候编译器会给出警告)
//    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
//        // 将解析结果存储在HashMap中
//        Map<String, String> map = new HashMap<String, String>();
//
//        // 从request中取得输入流
//        InputStream inputStream = request.getInputStream();
//        // 读取输入流
//        SAXReader reader = new SAXReader();
//        Document document = reader.read(inputStream);
//        // 得到xml根元素
//        Element root = document.getRootElement();
//        // 得到根元素的所有子节点
//        List<Element> elementList = root.elements();
//
//        // 遍历所有子节点
//        for (Element e : elementList)
//            map.put(e.getName(), e.getText());
//
//        // 释放资源
//        inputStream.close();
//        inputStream = null;
//
//        return map;
//    }
	
	/**
	 * @Description: TODO 从用户请求中获取请求消息并转换成xml字符串 
	 * 如果不用此方法进行转换，也可使用dom4J等工具进行转换存放到map中，如parseXml事例代码（）需要引入相关类包）
	 * @Title: getReqstMessageXml 
	 * @param @param request
	 * @param @return    参数  消息xml字符串
	 * @return String    返回类型  
	 * @throws
	 */
	public static String getReqstMessageXml(HttpServletRequest request){
		ServletInputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IO错误", e);
		}
		Scanner scan = new Scanner(inputStream, "UTF-8");//从指定流中构造一个新的扫描器
		StringBuffer sb = new StringBuffer();
		while(scan.hasNextLine()){
			sb.append(scan.nextLine()).append("\r\n");
		}
		scan.close();
		logger.info("xml字符串是：\r\n"+sb.toString());
		return sb.toString();
	}
	/**
	 * @Description: TODO 将请求的xml字符串转换为最全的请求实体
	 * @Title: xmlToMessage 
	 * @param @param is
	 * @param @return    参数  请求xml字符串
	 * @return Message_all    返回类型  最全的消息实体
	 * @throws
	 */
	public static AllMessage xmlToAllMessage(String requestMessageXml){
		XStream xStream = new XStream();
		//下面三行不加也行，但是会报一个警告
//		Class<?>[] classes = new Class[] {AllMessage.class, BaseMessage.class, EventLocationMessage.class
//				, EventMenuMessage.class, EventSubscribeMessage.class, EventTicketMessage.class
//				, ImageMessage.class, LinkMessage.class, TextMessage.class, VideoMessage.class, VioceMessage.class};
		Class<?>[] classes = new Class[] {AllMessage.class};
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(classes);
		xStream.alias("xml", AllMessage.class);
		AllMessage allMessage = (AllMessage) xStream.fromXML(requestMessageXml);
		logger.info("消息所转换的对象是："+allMessage.toString());
		return allMessage;
	}
	
	
//	public static TextMessage xmlToTextMessage(InputStream is){
//		Scanner scan = new Scanner(is, "UTF-8");//从指定流中构造一个新的扫描器
//		StringBuffer sb = new StringBuffer();
//		while(scan.hasNextLine()){
//			sb.append(scan.nextLine()).append("\r\n");
//		}
//		scan.close();
//		System.out.println("xml字符串是：\r\n"+sb.toString());
//		XStream xStream = new XStream();
//		//下面三行不加也行，但是会报一个警告
//		Class<?>[] classes = new Class[] {AllMessage.class, BaseMessage.class, EventLocationMessage.class
//				, EventMenuMessage.class, EventSubscribeMessage.class, EventTicketMessage.class
//				, ImageMessage.class, LinkMessage.class, TextMessage.class, VideoMessage.class, VioceMessage.class};
//		XStream.setupDefaultSecurity(xStream);
//		xStream.allowTypes(classes);
//		xStream.alias("xml", AllMessage.class);
//		AllMessage allMessage = (AllMessage) xStream.fromXML(sb.toString());
//		System.out.println("消息所转换的对象是：\r\n"+allMessage.toString());
//		return allMessage;
//	}
	
//	public static String textToXml(TextMessage textMessage){
//		
//	}
	
	/**
	 * @Description: TODO 将响应文本消息实体转换为响应xml 
	 * @Title: textMessageToXml 
	 * @param @param text
	 * @param @return    参数  文本消息实体
	 * @return String    返回类型  
	 * @throws
	 */
	public static String textMessageToXml(TextMessage text){
//		XStream xStream = new XStream();
		xStream.alias("xml", TextMessage.class);
		String xml = xStream.toXML(text);
		logger.info("响应的文本消息是：\r\n"+xml);
		return xml;
	}
	
	/**
	 * @Description: TODO 将响应图片消息实体转换为响应xml 
	 * @Title: imageMessageToXml 
	 * @param @param image
	 * @param @return    参数  图片消息实体
	 * @return String    返回类型  
	 * @throws
	 */
	public static String imageMessageToXml(ImageMessage image){
		xStream.alias("xml", ImageMessage.class);
		xStream.alias("Image", Image.class);
		String xml = xStream.toXML(image);
		logger.info("响应的文本消息是：\r\n"+xml);
		return xml;
	}
	
	/**
	 * @Description: TODO 将响应语音消息实体转换为响应xml 
	 * @Title: voiceMessageToXml 
	 * @param @param voice
	 * @param @return    参数  语音消息实体
	 * @return String    返回类型  
	 * @throws
	 */
	public static String voiceMessageToXml(VoiceMessage voice){
		xStream.alias("xml", VoiceMessage.class);
		xStream.alias("Voice", Voice.class);
		String xml = xStream.toXML(voice);
		logger.info("响应的语音消息是：\r\n"+xml);
		return xml;
	}
	
	/**
	 * @Description: TODO 将响应视频消息实体转换为响应xml 
	 * @Title: videoMessageToXml 
	 * @param @param video
	 * @param @return    参数  视频消息实体
	 * @return String    返回类型  
	 * @throws
	 */
	public static String videoMessageToXml(VideoMessage video){
		xStream.alias("xml", VideoMessage.class);
		xStream.alias("Video", Video.class);
		String xml = xStream.toXML(video);
		logger.info("响应的视频消息是：\r\n"+xml);
		return xml;
	}
	
	/**
	 * @Description: TODO 将响应音乐消息实体转换为响应xml 
	 * @Title: musicMessageToXml 
	 * @param @param music
	 * @param @return    参数  音乐消息实体
	 * @return String    返回类型  
	 * @throws
	 */
	public static String musicMessageToXml(MusicMessage music){
		xStream.alias("xml", MusicMessage.class);
		xStream.alias("Music", new Music().getClass());
		String xml = xStream.toXML(music);
		logger.info("响应的音乐消息是：\r\n"+xml);
		return xml;
	}
	
	/**
	 * @Description: TODO 将响应图文消息实体转换为响应xml 
	 * @Title: newsMessageToXml 
	 * @param @param news
	 * @param @return    参数  图文消息实体
	 * @return String    返回类型  
	 * @throws
	 */
	public static String newsMessageToXml(NewsMessage news){
		xStream.alias("xml", NewsMessage.class);
		xStream.alias("item", Article.class);
		String xml = xStream.toXML(news);
		logger.info("响应的图文消息是：\r\n"+xml);
		return xml;
	}
	
	public static void main(String[] args) {
		String xml = "<xml><ToUserName><![CDATA[gh_2739f5b0d08f]]></ToUserName><FromUserName><![CDATA[oR70a1ZqENldpxKNbKhFeaLzbdsE]]></FromUserName><CreateTime>1510121704</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[恩]]></Content><MsgId>6485923332137196955</MsgId></xml>";
		XStream xStream = new XStream();
		Class<?>[] classes = new Class[] {AllMessage.class};
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(classes);
		xStream.alias("xml", AllMessage.class);
		AllMessage message = (AllMessage) xStream.fromXML(xml);
		System.out.println(message);
		
	}
	
	/**
	 * 扩展xstream，使其支持CDATA块
	 */
	private static XStream xStream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
//                boolean cdata = true;
                //记录当前所解析的xml标签名
                String xmlName = null;
                //@SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                	xmlName = name;
                    super.startNode(name, clazz);
                }
                
                //不需要添加CDATA的标签名称
                private final String strName = "CreateTime,ArticleCount";
                protected void writeText(QuickWriter writer, String text) {
                    if (strName.contains(xmlName)) {
                    	writer.write(text);
                    } else {
                    	writer.write("<![CDATA[");
                    	writer.write(text);
                    	writer.write("]]>");
                    }
                }
            };
        }
    });
}
