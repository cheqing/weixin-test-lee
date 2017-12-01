package com.iyunr.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iyunr.controller.WxMainController;

/**
 * @Description: TODO 从properties文件中获取信息的工具类  
 * @author Administrator  
 * @date 2017年11月8日
 */
public class ConfigureUtil {
	private final static Logger logger = LoggerFactory.getLogger(WxMainController.class);
	private static Properties properties;
	
	static{
		InputStream is = ConfigureUtil.class.getClassLoader().getResourceAsStream("application.properties");//通过类加载器返回作为输入流读取配置文件 
		properties = new Properties();
		try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("读取配置文件conf.properties文件失败", e);
		}
	}
	
	/**
	 * @Description: TODO 从配置文件中获取属性值  
	 * @Title: getVal 
	 * @param @param key 键
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	public static String getVal(String key){
		return properties.getProperty(key);
	}
	
	public static void main(String[] args) {
		System.out.println(ConfigureUtil.getVal("server.port"));
	}
}
