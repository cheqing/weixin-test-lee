package com.iyunr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iyunr.config.AccessTokenSchedulingConfig;
import com.iyunr.entity.Oauth2Token;
import com.iyunr.entity.User;
import com.iyunr.entity.WeixinCommonParam;
import com.iyunr.service.UserService;
import com.iyunr.util.Oauth2TokenUtil;

/**
 * @Description: TODO 网页授权流程controller  
 * @author Administrator  
 * @date 2017年11月16日
 */
@Controller
@RequestMapping("/oauth")
public class OauthTokenController {
	@Autowired
	private WeixinCommonParam weixinCommonParam;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/redirectOauth")
	public String redirectOauth(){
		return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxbefffc2aefc4f25c&redirect_uri=http%3A%2F%2F18t826y954.imwork.net%2Foauth%2Fcode&response_type=code&scope=snsapi_base&state=state#wechat_redirect";
	}
	
	@RequestMapping(value="/code")
	public String getCode(Model model, String code){
		String appid = weixinCommonParam.getAppid();
		String secret = weixinCommonParam.getAppsecret();
		Oauth2Token token = this.getToken(appid, secret, code);
		
		//根据openid获取用户信息
		User user = userService.queryWx_user(AccessTokenSchedulingConfig.ACCESSTOKEN, token.getOpenid(), "zh_CN");
		System.out.println("用户："+user);
		model.addAttribute("token", token);
		model.addAttribute("user", user);
		return "user";
	}
	
	/**
	 * @Description: TODO 根据appid, secret, code获取token 
	 * @Title: getToken 
	 * @param @param appid
	 * @param @param secret
	 * @param @param code
	 * @param @return    参数  
	 * @return Oauth2Token    返回类型  
	 * @throws
	 */
	public Oauth2Token getToken(String appid, String secret, String code){
		Oauth2Token token = Oauth2TokenUtil.getOauth2AccessToken(appid, secret, code);
		System.out.println("token对象是："+token);
		return token;
	}
}
