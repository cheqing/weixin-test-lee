package com.iyunr.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iyunr.service.UserService;

/**
 * 项目测试类
 * @author Administrator
 *
 */
@Controller
public class HelloController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	@ResponseBody	//@ResponseBody注解加不加都能返回
	public String home(HttpServletRequest request){
		return "hello world！！";
	}
	
	@RequestMapping(value="/auth/user")
	public String user(String code){
		System.out.println(code);
		return "user";
	}
	
	/**
	 * localhost:8080/iyunr-pro
     * @Title: index  
     * @Description: TODO(这里用一句话描述这个方法的作用)  
     * @param @return    参数  
     * @return String    返回类型  
     * @throws
	 */
	@RequestMapping("/")
	public String index(){
//		return "/static/index";
		return "/demo/index";
	}
	
	@RequestMapping("/about.html")
	public String about(){
		return "/demo/about";
	}
	
	/**
	 * localhost:8080/iyunr-pro/username?name=tom
	 * @Title: getAll  
	 * @Description: TODO(通过姓名查找人员)  
	 * @param @param name 人员姓名
	 * @param @return    人员信息对象集合  
	 * @return List<User>    返回类型  
	 * @throws
	 */
//	@RequestMapping(value="/username")
//	public ResultJson getUserByName(String name){
//		return new ResultJson().success(userService.likeUserName(name), "SUCCESS");
//		return userService.likeUserName(name);
//	}
	
	@RequestMapping(value="/username1")
	public String getUserByName1(Model model, String name){
		model.addAttribute("data", "123456789");
		return "index";
	}
	
	/**
	 * localhost:8080/iyunr-pro/adduser
     * @Title: AddUser  
     * @Description: TODO(新增用户)  
     * @param @return    参数  
     * @return String    返回类型  
     * @throws
	 */
//	@RequestMapping(value="/adduser", method=RequestMethod.POST)
//	public String AddUser(@Validated @RequestBody User user){
//		int num = userService.addUser(user);
//		return "新增的条数是："+num+"---新增的数据id是："+user.getId();
//	}
	
	/**
	 * localhost:8080/iyunr-pro/uptUserById
     * @Title: uptUserById  
     * @Description: TODO(根据id修改用户信息)  
     * @param @return    参数  
     * @return String    返回类型  
     * @throws
	 */
//	@RequestMapping(value="/uptUserById", method=RequestMethod.PUT)
//	public String uptUserById(@RequestBody User user){
//		int uptNum = userService.uptUserById(user);
//		return "修改成功 "+uptNum+" 条数据";
//	}
	
	/**
	 * localhost:8080/iyunr-pro/delUserById/3
     * @Title: delUserById  
     * @Description: TODO(根据id删除用户)  
     * @param @param id
     * @param @return    参数  
     * @return String    返回类型  
     * @throws
	 */
//	@RequestMapping(value="/delUserById/{id}", method=RequestMethod.DELETE)
//	public String delUserById(@PathVariable("id") String id){
//		int delNum;
//		try {
//			delNum = userService.delUserById(id);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "删除失败";
//		}
//		return "删除成功 "+delNum+" 条数据";
//	}
	
	/**
	 * localhost:8080/iyunr-pro/testTran
	 * @Title: testTran  
	 * @Description: TODO(该方法用于测试调用多个更新方法时事物是否生效)  
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
//	@RequestMapping(value="/testTran", method=RequestMethod.GET)
//	public String testTran(){
//		User user = new User();
//		user.setId("4");
//		user.setAge(15);
//		user.setName("Boyce");
//
//		Map<String, Integer> data = userService.testTran(user, "4");
//		return "新增成功 "+data.get("addNum")+" 条，"+"修改成功 "+data.get("delNum")+" 条";
//	}
	
	
}
