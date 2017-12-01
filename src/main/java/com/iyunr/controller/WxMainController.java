package com.iyunr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iyunr.entity.message.req.AllMessage;
import com.iyunr.mapper.ProccessRequestMessageMapper;
import com.iyunr.service.impl.ProccessMessageRequestServiceImpl;
import com.iyunr.util.MessageUtil;
import com.iyunr.util.SignUtil;

/**
 * @Description: TODO 微信与开发者服务器交互接口
 * @author Administrator  
 * @date 2017年11月2日
 */

//@Controller
@RestController
@RequestMapping("/wx")
public class WxMainController {
	private final Logger logger = LoggerFactory.getLogger(WxMainController.class);
	String token = "weixin-pro-test";
	
	@Autowired
	private ProccessRequestMessageMapper proccessRequestMessage;
	@Autowired
	private ProccessMessageRequestServiceImpl proccessMessageRequestService;
	
	/**
	 * @Description: TODO 验证消息是否来自于微信服务器，如果通过校验后需要将echostr参数返回
	 * @Title: doGet 
	 * @param @param request
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	@RequestMapping(value="/join", method=RequestMethod.GET)
	@ResponseBody
	public String wxJoin(HttpServletRequest request){
		String signature = request.getParameter("signature");	//微信加密签名
		String timestamp = request.getParameter("timestamp");	//时间戳
		String nonce = request.getParameter("nonce");	//随机数
		String echostr = request.getParameter("echostr");	//随机字符串
		System.out.println(signature+", "+timestamp+", "+nonce+", "+echostr);
		if(SignUtil.validSign(signature, token, timestamp, nonce)){
			logger.info("微信接入成功！");
			return echostr;
		}
		logger.error("微信接入失败！");
		return "";
	}
	
	/**
	 * @Description: TODO  与微信服务器交互消息的入口
	 * @Title: wxMain 
	 * @param @return    参数  
	 * @return ResultJson    返回类型  
	 * @throws
	 */
	@RequestMapping(value="/join", method=RequestMethod.POST)
	@ResponseBody
	public String wxMain(HttpServletRequest request, HttpServletResponse response){
		try {
			//设置请求和响应的字符集
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
//			ProccessMessageRequestService proccess = new ProccessMessageRequestService();
			String resMessage = proccessMessageRequestService.proccessMessage(request);
			//此处模拟的业务是：当消息类型是文字和语音的时候，响应文字消息，如果是图片消息的请求，那么相应图片
			return resMessage;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("设置请求和响应字符集失败", e);
		}
		return null;
	}
	
}
