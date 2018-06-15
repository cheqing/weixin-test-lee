package com.iyunr.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @Description: TODO 登录工具类，使用sha1加密验证
 * @author Administrator  
 * @date 2017年11月3日
 */
public class SignUtil {
	/**
	 * 判断登录有效性
	 * 用来验证消息的确是来自微信服务器的，将token、timestamp、nonce三个参数进行字典排序，在讲三个参数字符串拼接成一个字符串进行sha1加密
	 * 将获得加密后的字符串与signature进行对比，一样的话则证明请求来源于微信
	 * @Title: validSign  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	 * @param @param token 密码
	 * @param @param timestamp 时间戳
	 * @param @param nonce 随机数
	 * @param @return    参数  
	 * @return boolean    判断返回值为true 或 false
	 * @throws
	 */
	public static boolean validSign(String signature, String token, String timestamp, String nonce){
		String[] paramArr = new String[]{token, timestamp, nonce};
		Arrays.sort(paramArr);//将数组按数字升序进行排序
		//将排序后的字符串进行拼接
		StringBuilder sb = new StringBuilder(paramArr[0]);
		sb.append(paramArr[1]).append(paramArr[2]);
		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");//为应用程序提供信息摘要算法的功能
			byte[] digest = md.digest(sb.toString().getBytes());
			ciphertext = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
	}
	
	/**
	 * 
	 * @Description: TODO 将字节数组转换为十六进制字符串
	 * @Title: byteToStr 
	 * @param @param byteArray
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
	private static String byteToStr(byte[] byteArray) {  
        String rst = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            rst += byteToHex(byteArray[i]);  
        }  
        return rst;  
    }  
    
	/**
	 * @Description: TODO 将字节转换为十六进制字符串
	 * @Title: byteToHex 
	 * @param @param b
	 * @param @return    参数  
	 * @return String    返回类型  
	 * @throws
	 */
    private static String byteToHex(byte b) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(b >>> 4) & 0X0F];  
        tempArr[1] = Digit[b & 0X0F];  
        String s = new String(tempArr);  
        return s;  
    }
}
