package com.iyunr.controllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iyunr.Application;
import com.iyunr.config.AccessTokenSchedulingConfig;
import com.iyunr.entity.AccessToken;
import com.iyunr.entity.Error_message;
import com.iyunr.entity.Menu_commonButton;
import com.iyunr.entity.Menu_complexButton;
import com.iyunr.entity.Menu_list;
import com.iyunr.entity.Oauth2Token;
import com.iyunr.entity.User;
import com.iyunr.resultJson.ResultJson;
import com.iyunr.service.AccessTokenService;
import com.iyunr.service.MenuService;
import com.iyunr.service.UserService;
import com.iyunr.util.HttpUtil;
import com.iyunr.util.Oauth2TokenUtil;

@RunWith(SpringJUnit4ClassRunner.class)	// SpringJUnit支持，由此引入Spring-Test框架支持！ 
@SpringBootTest(classes = Application.class)	// 指定我们SpringBoot工程的Application启动类
public class HelloTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AccessTokenService accessTokenService;
	
	@Autowired
	private MenuService menuService;
	
//	@Test
//	public void getUserByNameTest(){
//		String name = "tom";
//		List<User> data = userService.likeUserName(name);
//		System.out.println(data);
//	}
	
	@Test
	public void httpUtilTest(){
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxbefffc2aefc4f25c&secret=8586a45b15166c8ced0289d71fa1ac8c";
		String data = HttpUtil.sendHttpReq(url, "GET", null, "utf-8");
		JSONObject json = JSON.parseObject(data);
		AccessToken token = new AccessToken();
		token.setAccessToken(json.getString("access_token"));
		token.setExpiresIn(json.getString("expires_in"));
		System.out.println(token);
	}
	
	@Test
	public void insetAccessTokenTest(){
		AccessToken token = new AccessToken();
		token.setAccessToken("123456789987654321");
		token.setExpiresIn("7200");
		int num = accessTokenService.insertAccessToken(token);
		System.out.println(num+"----"+token.getId());
	}
	
	
	@Test
	public void insertMenu1(){
		Menu_commonButton button1 = new Menu_commonButton();
		button1.setType("scancode_push");
		button1.setName("扫码推事件a");
		button1.setKey("selfmenu_1_1");
		Menu_commonButton button2 = new Menu_commonButton();
		button2.setType("click");
		button2.setName("你是谁");
		button2.setKey("selfmenu_1_2");
		Menu_commonButton button3 = new Menu_commonButton();
		button3.setType("view");
		button3.setName("page1");
//		button3.setUrl("http://18t826y954.imwork.net/oauth/redirectOauth");
		button3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxbefffc2aefc4f25c&redirect_uri=http%3A%2F%2F18t826y954.imwork.net%2Foauth%2Fcode&response_type=code&scope=snsapi_base&state=state#wechat_redirect");
//		button3.setUrl("http://iworld.55555.io/auth/user");
		
		Menu_complexButton sub1 = new Menu_complexButton();
		sub1.setName("扫码a");
		sub1.setSub_button(new Menu_commonButton[]{button1, button2, button3});
		
		Menu_commonButton button4 = new Menu_commonButton();
		button4.setType("location_select");
		button4.setName("发送位置a");
		button4.setKey("rselfmenu_2_0");
		
		Menu_complexButton sub2 = new Menu_complexButton();
		sub2.setName("菜单a");
		sub2.setSub_button(new Menu_commonButton[]{button4});
		
		Menu_list menu = new Menu_list();
		menu.setButton(new Menu_complexButton[]{sub1, sub2});
		
		String menuJson = JSON.toJSONString(menu);
		System.out.println(menuJson);
		
		String data = menuService.createMenu(menuJson);
		System.out.println(data);
		
	}
	
	@Test
	public void batchqueryUserTest(){
		String data = userService.batchquery(AccessTokenSchedulingConfig.ACCESSTOKEN);
		Error_message resultjson = JSON.parseObject(data, Error_message.class);
		if(resultjson.getErrcode() == null){
			System.out.println(new ResultJson().success(data, "SUCCESS").toString());
		}
		System.out.println(new ResultJson().failure("微信用户列表查询失败！"));
	}
	
	@Test
	public void saveUserInfo(){
		//获取并保存用户信息
		User user = userService.queryWx_user(AccessTokenSchedulingConfig.ACCESSTOKEN, "oR70a1ZqENldpxKNbKhFeaLzbdsE", "zh_CN");
		System.out.println(user);
//		int userId = userService.insertUser(user);
		int userId = userService.insertUser(user);
		System.out.println(userId);
	}
	
	@Test
	public void uptUserToUnSubscribe(){
		//将用户改成已取消关注用户
		int uptNo = userService.uptUsertoUnSubscribe("oR70a1ZqENldpxKNbKhFeaLzbdsE");
		System.out.println("成功修改了"+uptNo+"条数据！");
	}
	
	@Test
	public void getTokenByCode(){
		String appid = "wxbefffc2aefc4f25c";
		String secret = "8586a45b15166c8ced0289d71fa1ac8c";
		Oauth2Token token = Oauth2TokenUtil.getOauth2AccessToken(appid, secret, "021gAJKh1oLa1w0mxfJh10ySKh1gAJKV");
		System.out.println(token);
	}
	
	
	public static void main(String[] args) {
	}
	
}
