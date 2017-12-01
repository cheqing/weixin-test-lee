package com.iyunr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.iyunr.config.AccessTokenSchedulingConfig;
import com.iyunr.controller.WxMainController;
import com.iyunr.entity.User;
import com.iyunr.entity.message.req.AllMessage;
import com.iyunr.entity.message.res.Article;
import com.iyunr.entity.message.res.ImageMessage;
import com.iyunr.entity.message.res.MusicMessage;
import com.iyunr.entity.message.res.NewsMessage;
import com.iyunr.entity.message.res.TextMessage;
import com.iyunr.entity.message.res.VideoMessage;
import com.iyunr.entity.message.res.VoiceMessage;
import com.iyunr.exceptionHandler.ServiceException;
import com.iyunr.service.ProccessMessageRequestService;
import com.iyunr.service.UserService;
import com.iyunr.util.HttpUtil;
import com.iyunr.util.MessageUtil;

/**
 * @Description: TODO 处理用户消息实现类  
 * @author Administrator  
 * @date 2017年11月9日
 */
@Service
public class ProccessMessageRequestServiceImpl implements ProccessMessageRequestService{
	private final Logger logger = LoggerFactory.getLogger(WxMainController.class);
	
	@Autowired
	private UserService userService;
	
	@Override
	public String proccessMessage(HttpServletRequest request) {
		String resMessage = null;
		//从请求中获取消息的xml字符串
		String msgXml = MessageUtil.getReqstMessageXml(request);
		//将xml字符串转换为最全的请求实体
		AllMessage allMessage = MessageUtil.xmlToAllMessage(msgXml);
		//获取消息类型
		String msgType = allMessage.getMsgType();
		//获取请求者微信信息
//		UserService userService = new UserService();
		User user = userService.queryWx_user(AccessTokenSchedulingConfig.ACCESSTOKEN, allMessage.getFromUserName(), "zh_CN");
		if(msgType.equals(MessageUtil.REQ_MSG_TYPE_TEXT)){
			String reqContent = allMessage.getContent();//获取请求消息内容
			String resContent = "我不认识你说什么，换一种打开方式可能会更好!";//设置响应默认回复
			TextMessage text = this.transTextMessage(allMessage);//转换成文本消息
			switch (reqContent) {
			case "1":
				StringBuffer sb = new StringBuffer("你好 "+user==null? "" : user.getNickname()+", 我是Lee，请回复如下数字选择服务：\r\n");
				sb.append("10   我会给你一个美炸的图片\r\n").append("11   我会给你一个倍儿有磁性的语音\r\n").append("12	  我会给你一个美滋滋的视频\r\n")
				.append("13   我会给你一首空灵的音乐\r\n").append("14   我会给你一个图文消息。");
				text.setContent(sb.toString());
				//将文本实体转换为响应xml
				resMessage = MessageUtil.textMessageToXml(text);
				break;
			case "10":
				//上传本地图片素材
//				String materialImageInfo = HttpUtil.uploadFromLocal(
//						"https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+AccessTokenSchedulingConfig.ACCESSTOKEN+"&type=image", 
//						"C:\\Users\\Administrator\\Desktop\\PICS\\58eaf03c0dab3.jpg", "utf-8");
				
				//上传网络图片素材，这里调用微信素材管理中的新增临时素材接口，临时接口的media_id也是可以复用的，会在微信后台保存三天，三天后media_id失效，在有效期内的话我们就可以不用请求网络图片了，这个功能待扩展
				String materialImageInfo = HttpUtil.uploadFromInter("https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+AccessTokenSchedulingConfig.ACCESSTOKEN+"&type=image", 
						"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510237482285&di=817cd50c4b7427af54eac86d7b7dbbd8&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F151008%2F14-15100Q455593Y.jpg");
				ImageMessage image = this.transImageMessage(allMessage);//转换成图片消息
				@SuppressWarnings("unchecked")
				Map<String, Object> imageMap = (Map<String, Object>)JSON.parse(materialImageInfo);
				image.getImage().setMediaId(imageMap.get("media_id").toString());
				//将图片实体转换为响应xml
				resMessage = MessageUtil.imageMessageToXml(image);
				break;
			case "11":
				//上传本地素材
				String materialVoiceInfo = HttpUtil.uploadFromLocal(
						"https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+AccessTokenSchedulingConfig.ACCESSTOKEN+"&type=voice", 
						"D:\\Google下载\\GYB06157.mp3", "utf-8");
				VoiceMessage voice = this.transVoiceMessage(allMessage);
				@SuppressWarnings("unchecked")
				Map<String, Object> voiceMap = (Map<String, Object>)JSON.parse(materialVoiceInfo);
				voice.getVoice().setMediaId(voiceMap.get("media_id").toString());
				//将图片实体转换为响应xml
				resMessage = MessageUtil.voiceMessageToXml(voice);
				break;
			case "12":
				//上传本地素材
				String materialVideoInfo = HttpUtil.uploadFromLocal(
						"https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+AccessTokenSchedulingConfig.ACCESSTOKEN+"&type=video", 
						"C:\\Users\\Administrator\\Desktop\\PICS\\831891d01119c740d9c7a5a6f72951fc.mp4", "utf-8");
				VideoMessage video = this.transVideoMessage(allMessage);
				@SuppressWarnings("unchecked")
				Map<String, Object> videoMap = (Map<String, Object>)JSON.parse(materialVideoInfo);
				video.getVideo().setMediaId(videoMap.get("media_id").toString());
				video.getVideo().setTitle("美滋滋的视频");
				video.getVideo().setDescription("没啥可说的，看就得了。");
				//将图片实体转换为响应xml
				resMessage = MessageUtil.videoMessageToXml(video);
				break;
			case "13":
				//上传本地素材
				String materialMusicInfo = HttpUtil.uploadFromInter(
						"https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+AccessTokenSchedulingConfig.ACCESSTOKEN+"&type=image", 
						"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510237482285&di=817cd50c4b7427af54eac86d7b7dbbd8&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F151008%2F14-15100Q455593Y.jpg");
				MusicMessage music = this.transMusicMessage(allMessage);
				@SuppressWarnings("unchecked")
				Map<String, Object> musicMap = (Map<String, Object>)JSON.parse(materialMusicInfo);
				music.getMusic().setTitle("红豆-方大同");
				music.getMusic().setDescription("方大同的红豆比王菲唱的更有滋味");
				music.getMusic().setMusicUrl("http://qqma.tingge123.com:823/mp3/2016-05-27/1464299479.mp3");
//				music.getMusic().setMusicUrl("http://cloudshui.com/bowen/music/tonghuazhen_chenyifa.mp3");
				music.getMusic().setHQMusicUrl("http://qqma.tingge123.com:823/mp3/2016-05-27/1464299479.mp3");
//				music.getMusic().setHQMusicUrl("https://music.163.com/song?id=432506345");
				music.getMusic().setThumbMediaId(musicMap.get("media_id").toString());
				//将图片实体转换为响应xml
				resMessage = MessageUtil.musicMessageToXml(music);
				break;
			case "14":
				List<Article> articleList = new ArrayList<Article>();
				Article ar1 = new Article();
				ar1.setTitle("误点，这里有我的美照！！");
				ar1.setDescription("没什么可描述的");
				ar1.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1510237482285&di=817cd50c4b7427af54eac86d7b7dbbd8&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F151008%2F14-15100Q455593Y.jpg");
				ar1.setUrl("http://iworld.55555.io");
				
				Article ar2 = new Article();
				ar2.setTitle("湛江谁有这种女儿，请给我来一打！");
				ar2.setDescription("没什么可描述的");
				ar2.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
				ar2.setUrl("http://iworld.55555.io/");
				
				Article ar3 = new Article();
				ar3.setTitle("today is a nice day");
				ar3.setDescription("没什么可描述的");
				ar3.setPicUrl("https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D220/sign=700b73d924f5e0fef1188e036c6134e5/d53f8794a4c27d1e520f470412d5ad6edcc438c0.jpg");
				ar3.setUrl("https://mp.weixin.qq.com/s?__biz=MzU3NzI5Njc5Nw==&tempkey=OTMwX1ZBYm5vVE5mTVNrbWppYTdrVmRRM0l1M3JZa2luRGdqNm80cUtyNzZycExsQVdCNXBSTU8wQkZvRHhId3B4elBkZUo1UDRubjRqUGdPTHYyWEZyTWxLUXJVNnhQVjVMZVVNczBzYl95NmY3Z01VcFR3RW9nOXpMYWxERW5COEFVQ1BOeDVyRVlqQzFwbjBpNGtadkIteTNoc19xcmIxRHk0VHVyQVF%2Bfg%3D%3D&chksm=7d0781e44a7008f22faa70f924e9ce8b73a9b916acf6e016d62a7a3fc60e49f75520098eb7ed#rd");
				
				articleList.add(ar1);
				articleList.add(ar2);
				articleList.add(ar3);
				
				NewsMessage news = this.transNewsMessage(allMessage);
				news.setArticles(articleList);
				news.setArticleCount(articleList.size());
				//将图文实体转换为响应xml
				resMessage = MessageUtil.newsMessageToXml(news);
				break;
			default:
				text.setContent(resContent);
				//将文本实体转换为xml
				resMessage = MessageUtil.textMessageToXml(text);
			}
		}else if(msgType.equals(MessageUtil.REQ_MSG_TYPE_EVENT) && "subscribe".equals(allMessage.getEvent())){//关注事件
			//为关注者推送服务消息
			TextMessage text = this.transTextMessage(allMessage);//转换成文本消息
			StringBuffer sb = new StringBuffer(user==null? "" : user.getNickname()+", 你好，欢迎关注本公众号,请回复如下数字选择服务：\r\n");
			sb.append("10   我会给你一个美炸的图片\r\n").append("11   我会给你一个倍儿有磁性的语音\r\n").append("12	  我会给你一个美滋滋的视频\r\n")
			.append("13   我会给你一首空灵的音乐\r\n").append("14   我会给你一个图文消息。");
			text.setContent(sb.toString());
			//将文本实体转换为响应xml
			resMessage = MessageUtil.textMessageToXml(text);
			
			//获取并保存用户信息
			User userInfo = userService.queryWx_user(AccessTokenSchedulingConfig.ACCESSTOKEN, allMessage.getFromUserName(), "zh_CN");
//			userser.queryWx_user(AccessTokenSchedulingConfig.ACCESSTOKEN, allMessage.getFromUserName(), "zh_CN");
			if(userInfo != null){
				userService.insertUser(userInfo);
			}else{
				logger.error("新关注的用户保存数据库失败");
			}
		}else if(msgType.equals(MessageUtil.REQ_MSG_TYPE_EVENT) && "unsubscribe".equals(allMessage.getEvent())){//取消关注事件
			//将用户改成已取消关注用户
			int uptNo = userService.uptUsertoUnSubscribe(allMessage.getFromUserName());
			if(uptNo < 0){
				new ServiceException("将用户["+allMessage.getFromUserName()+"]改成已取消关注用户失败");
			}
		}else if(msgType.equals(MessageUtil.REQ_MSG_TYPE_EVENT) && "CLICK".equals(allMessage.getEvent())){//点击事件
			User clickUser = userService.queryWx_user(AccessTokenSchedulingConfig.ACCESSTOKEN, allMessage.getFromUserName(), "zh_CN");
			TextMessage text = this.transTextMessage(allMessage);//转换成文本消息
			text.setContent(clickUser.toString());
			//将文本实体转换为响应xml
			resMessage = MessageUtil.textMessageToXml(text);
		}
		return resMessage;
	}
	
	
	/**
	 * @Description: TODO 响应文本消息转换 
	 * @Title: TransTextMessage 
	 * @param @param allMessage
	 * @param @return    参数  
	 * @return TextMessage    返回类型  
	 * @throws
	 */
	public TextMessage transTextMessage(AllMessage allMessage){
		TextMessage text = new TextMessage();
		text.setToUserName(allMessage.getFromUserName());
		text.setFromUserName(allMessage.getToUserName());
		text.setMsgType("text");
		text.setCreateTime(new Date().getTime());
		return text;
	}
	
	/**
	 * @Description: TODO 响应图片消息转换 
	 * @Title: transImageMessage 
	 * @param @param allMessage
	 * @param @return    参数  
	 * @return ImageMessage    返回类型  
	 * @throws
	 */
	public ImageMessage transImageMessage(AllMessage allMessage){
		ImageMessage image = new ImageMessage();
		image.setToUserName(allMessage.getFromUserName());
		image.setFromUserName(allMessage.getToUserName());
		image.setCreateTime(allMessage.getCreateTime());
		image.setMsgType("image");
		return image;
	}
	
	/**
	 * @Description: TODO 响应语音消息转换 
	 * @Title: transImageMessage 
	 * @param @param allMessage
	 * @param @return    参数  
	 * @return VoiceMessage    返回类型  
	 * @throws
	 */
	public VoiceMessage transVoiceMessage(AllMessage allMessage){
		VoiceMessage voice = new VoiceMessage();
		voice.setToUserName(allMessage.getFromUserName());
		voice.setFromUserName(allMessage.getToUserName());
		voice.setCreateTime(allMessage.getCreateTime());
		voice.setMsgType("voice");
		return voice;
	}
	
	/**
	 * @Description: TODO 响应视频消息转换 
	 * @Title: transVideoMessage 
	 * @param @param allMessage
	 * @param @return    参数  
	 * @return VideoMessage    返回类型  
	 * @throws
	 */
	public VideoMessage transVideoMessage(AllMessage allMessage){
		VideoMessage video = new VideoMessage();
		video.setToUserName(allMessage.getFromUserName());
		video.setFromUserName(allMessage.getToUserName());
		video.setCreateTime(allMessage.getCreateTime());
		video.setMsgType("video");
		return video;
	}
	
	/**
	 * @Description: TODO 响应音乐消息转换 
	 * @Title: transMusicMessage 
	 * @param @param allMessage
	 * @param @return    参数  
	 * @return MusicMessage    返回类型  
	 * @throws
	 */
	public MusicMessage transMusicMessage(AllMessage allMessage){
		MusicMessage music = new MusicMessage();
		music.setToUserName(allMessage.getFromUserName());
		music.setFromUserName(allMessage.getToUserName());
		music.setCreateTime(allMessage.getCreateTime());
		music.setMsgType("music");
		return music;
	}
	
	/**
	 * @Description: TODO 响应图文消息转换 
	 * @Title: transNewsMessage 
	 * @param @param allMessage
	 * @param @return    参数  
	 * @return NewsMessage    返回类型  
	 * @throws
	 */
	public NewsMessage transNewsMessage(AllMessage allMessage){
		NewsMessage news = new NewsMessage();
		news.setToUserName(allMessage.getFromUserName());
		news.setFromUserName(allMessage.getToUserName());
		news.setCreateTime(allMessage.getCreateTime());
		news.setMsgType("news");
		return news;
	}
	
	/**
	 * 判断是否是QQ表情（针对旧表情有效）
	 *
	 * @param content
	 * @return
	 */
	public static boolean isQqFace(String content) {
        boolean result = false;

        // 判断QQ表情的正则表达式
        String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
        Pattern p = Pattern.compile(qqfaceRegex);
        Matcher m = p.matcher(content);
        if (m.matches()) {
        result = true;
        }
        return result;
    }

}
