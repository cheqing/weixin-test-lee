package com.iyunr.util;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iyunr.entity.Oauth2Token;
import com.iyunr.entity.User;

/**
 * @Description: TODO 网页授权oauth2.0工具类  
 * 获取code后，请求以下链接获取access_token：  https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code 
 * 
	参数	是否必须	说明
	appid	是	公众号的唯一标识
	secret	是	公众号的appsecret
	code	是	填写第一步获取的code参数
	grant_type	是	填写为authorization_code   
	
	
	网页授权分为四步： 
1. 引导用户进入授权页面同意授权，获取code 
2. 通过code换取网页授权access_token（与基础支持中的access_token不同） 
3. 如果需要，开发者可以刷新网页授权access_token，避免过期 
4. 通过网页授权access_token和openid获取用户基本信息（支持UnionID机制）
 * @author Administrator  
 * @date 2017年11月16日
 */
public class Oauth2TokenUtil {
	private static Logger logger = LoggerFactory.getLogger(Oauth2TokenUtil.class);
	/**
	 * @Description: TODO 请求获取网页授权token 
	 * @Title: getOauth2AccessToken 
	 * @param @param appid 公众号的唯一标识
	 * @param @param secret 公众号的appsecret
	 * @param @param code 填写第一步获取的code参数
	 * @param @return    参数  
	 * @return Oauth2Token    返回类型  
	 * @throws
	 */
	public static Oauth2Token getOauth2AccessToken(String appid, String secret, String code){
		Oauth2Token oauthToken = null;
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
//		requestUrl.replaceAll("APPID", appid);
//		requestUrl.replaceAll("SECRET", secret);
//		requestUrl.replaceAll("CODE", code);
		//获取网页凭证token
		String resResult = HttpUtil.sendHttpReq(requestUrl, "GET", null, "UTF-8");
//		JSONObject jsonObj = HttpUtil.sendHttpsReq(requestUrl, "GET", null);
		//将resResult字符串转换成json
		JSONObject obj  = JSON.parseObject(resResult);
		if(obj.getString("errmsg") == null || "".equals(obj.getString("errmsg"))){//请求成功
			oauthToken = new Oauth2Token();
			oauthToken.setAccessToken(obj.getString("access_token"));
			oauthToken.setExpiresIn(obj.getLong("expires_in"));
			oauthToken.setRefreshToken(obj.getString("refresh_token"));
			oauthToken.setOpenid(obj.getString("openid"));
			oauthToken.setScope(obj.getString("scope"));
			return oauthToken;
		}
		logger.error("获取网页授权失败！--"+obj.getInteger("errcode")+"-"+obj.getString("errmsg"));
		return null;
	}
	
	/**
	 * @Description: TODO 通过网页授权的token和用户的openid获取用户信息 
	 * 如果网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息了。
	 * @Title: getUserByOauth2Token 
	 * @param @param accessToken 网页授权凭证
	 * @param @param openId 用户的唯一标识
	 * @param @return    参数  
	 * @return User    返回类型  
	 * @throws
	 */
	public static User getUserByOauth2Token(String accessToken, String openId){
		User user = null;
		//拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN ";
		requestUrl.replaceAll("ACCESS_TOKEN", accessToken).replaceAll("OPENID", openId);
		//通过网页授权获取用户信息
		String resResult = HttpUtil.sendHttpReq(requestUrl, "GET", null, "UTF-8");
		//将resResult字符串转换成json
				JSONObject obj  = JSON.parseObject(resResult);
		if(obj.getString("errmsg") == null || "".equals(obj.getString("errmsg"))){//请求成功
			user = new User();
			user.setOpenid(obj.getString("openid"));
			user.setNickname(obj.getString("nickname"));
			user.setSex(obj.getIntValue("sex"));
			user.setProvince(obj.getString("province"));
			user.setCity(obj.getString("city"));
			user.setCountry(obj.getString("country"));
			user.setHeadimgurl(obj.getString("headimgurl"));
			user.setPrivilege(obj.getString("privilege"));
			user.setUnionid(obj.getString("unionid"));
			return user;
		}
		logger.error("获取用户信息失败！--"+obj.getInteger("errcode")+"-"+obj.getString("errmsg"));
		return null;
	}
	
	/**
	 * @Description: TODO  uri编码（utf-8）
	 * @Title: urlEncodeUTF8 
	 * @param @param source
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public static void main(String[] args) {
		System.out.println(Oauth2TokenUtil.urlEncodeUTF8("http://18t826y954.imwork.net/oauth/code"));
	}
}
