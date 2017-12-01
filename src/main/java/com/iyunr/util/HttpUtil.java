package com.iyunr.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description: TODO http连接工具  
 * @author Administrator  
 * @date 2017年11月3日
 */
public class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	/**
	 * 发送http请求
	 * @Description: TODO 
	 * @Title: sendHttpReq 
	 * @param @param url 请求地址
	 * @param @param method 请求方法
	 * @param @param param 请求参数
	 * @param @param charset 字符集
	 * @param @return    参数  
	 * @return String    返回请求结果
	 * @throws
	 */
	public static String sendHttpReq(String requestUrl, String requestMethod, String requestParam, String charset){
		HttpURLConnection httpURLConnection = null;	//生成请求连接到Http服务器
		BufferedOutputStream bufferedOutputStream = null;//缓冲输出流
		BufferedReader bufferedReader = null;//读取字符串输入流
		URL urlObj;
		try {
			urlObj = new URL(requestUrl);
			httpURLConnection = (HttpURLConnection) urlObj.openConnection();//连接url所引用的远程对象
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.error("HttpUtil类sendHttpReq方法-出现了使用未知协议错误", e);
		}catch (IOException  e) {
			e.printStackTrace();
			logger.error("HttpUtil类sendHttpReq方法-出现了I/O异常错误", e);
		}
		httpURLConnection.setDoInput(true);//读取指定URL服务器的资源
		if(requestParam != null){
			httpURLConnection.setDoOutput(true);//写入数据到指定url
		}
		httpURLConnection.setUseCaches(false);//不允许连接使用缓存
		httpURLConnection.setReadTimeout(1000*60);//设置读超时，单位毫秒，此处设置为一分钟
		httpURLConnection.setConnectTimeout(1000*60);//设置建立连接超时，单位毫秒，此处设置为一分钟
		try {
			httpURLConnection.setRequestMethod(requestMethod.toUpperCase());
		} catch (ProtocolException e) {
			e.printStackTrace();
			logger.error("HttpUtil类sendHttpReq方法-无法重置方法或者请求的方法对 HTTP 无效", e);
		}
		
		try {
			if(requestParam != null){
				bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
				bufferedOutputStream.write(requestParam.getBytes());
				bufferedOutputStream.flush();  
				bufferedOutputStream.close();  
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("HttpUtil类sendHttpReq方法-缓冲输出流出现了I/O异常错误", e);
		}
		
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), charset));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("HttpUtil类sendHttpReq方法-读取字符输入流出现了I/O异常错误", e);
		}
		
		//提供了与StringBuffer兼容的api，当字符缓冲区被单个线程所使用时，优先使用此类，因为其比StringBuffer要快  
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		//读取一个文本行操作
		try {
			if((line = bufferedReader.readLine()) != null){
				stringBuilder.append(line).append("\n");
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("HttpUtil类sendHttpReq方法-读取字符输入流出现了I/O异常错误", e);
		}
		//指示近期服务器不太可能有其他请求
		httpURLConnection.disconnect();
		return stringBuilder.toString();
	}
	
	/**
	 * @Description: TODO 发送https请求工具 
	 * @Title: sendHttpsReq 
	 * @param @param requestUrl 请求地址
	 * @param @param requestMethod 请求方法（GET/POST）
	 * @param @param param 请求参数
	 * @param @return    参数  
	 * @return JSONObject    返回类型  
	 * @throws
	 */
	public static JSONObject sendHttpsReq(String requestUrl, String requestMethod, String param){
		JSONObject jsonObj = null;
		try {
			//创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = {new MyX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
			
            //创建连接
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            
            // 当param不为null时向输出流写数据
            if (null != param) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(param.getBytes("UTF-8"));
                outputStream.close();
            }
            
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObj = JSONObject.parseObject(buffer.toString());
		} catch (ConnectException  e) {
			logger.error("连接超时：{}", e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("https请求异常：{}", e);
			e.printStackTrace();
		}
		return jsonObj;
	}
	
	/** 
     * 实现关闭流、关闭连接的功能 
     * @param reader 关闭字符读取流 
     * @param inputStream 关闭输入流 
     * @param outputStream 关闭输出流 
     * @param httpURLConnection 关闭http服务器连接 
     */  
    public static void closeAll(Reader reader, InputStream inputStream, OutputStream outputStream, HttpURLConnection httpURLConnection) {  
        //关闭字符流  
        if(reader != null){  
            try {  
                reader.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
                logger.error("HttpUtil类closeAll方法-关闭字符流reader时出现了I/O异常错误", e);
            }  
        }  
        if(inputStream != null){  
            try {  
                inputStream.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
                logger.error("HttpUtil类closeAll方法-关闭inputStream时出现了I/O异常错误", e);
            }  
        }  
        if(outputStream != null){  
            try {  
                outputStream.close();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
                logger.error("HttpUtil类closeAll方法-关闭outputStream时出现了I/O异常错误", e); 
            }  
        }  
        if(httpURLConnection != null){  
            httpURLConnection.disconnect();  
        }  
    }  
    
    
    /** 
     * 实现本地上传素材的功能 
     * @param url 请求地址 
     * @param filePath 文件路径 (必须是绝对路径)
     * @param charset 字符编码方式 
     * @return String 表单地址 
     */  
    public static String uploadFromLocal(String uploadUrl, String fileUrl, String charset) {
		//通常文件上传是通过html表单进行的，通过HttpURLConnection 可以不经过浏览器直接在服务器端进行表单的POST提交，完成文件上传功能！
		HttpURLConnection conn = null;//生成请求连接到Http服务器  
		File file = new File(fileUrl);
		StringBuilder stringBuilder = null;
		try {  
			//1.建立连接
			URL url = new URL(uploadUrl);
			conn = (HttpURLConnection) url.openConnection();//连接url所引用的远程对象 
			
			// 1.1输入输出设置,发送POST请求必须设置如下两行
			conn.setDoOutput(true);//允许读取指定url服务器的资源
			conn.setDoInput(true);//允许写入数据到指定url服务器
			conn.setUseCaches(false);//不允许连接使用缓存
			conn.setReadTimeout(1000 * 60);//设置读超时，此处设置为1分钟，单位为毫秒
			conn.setConnectTimeout(1000 * 60);//设置建立连接超时，此处设置为1分钟，单位为毫秒
			conn.setRequestMethod("POST");//设置请求方法  
			//1.2设置请求头信息
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", charset);
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			
			//1.3设置边界, 定义数据分割符
			String boundary = String.valueOf(System.currentTimeMillis());
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

			// 请求正文信息
	        // 第一部分：
	        //2.将文件头输出到微信服务器
			byte[] end_data = ("\r\n--" + boundary + "--\r\n").getBytes();// 定义最后数据分隔线
			StringBuilder sb = new StringBuilder();
			sb.append("--");// 必须多两道线
			sb.append(boundary);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"media\";filename=\"" + file.getName() + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			
			// 获得输出流
			OutputStream out = null;
			out = new DataOutputStream(conn.getOutputStream());  
			// 将表头写入输出流中：输出表头
			byte[] data = sb.toString().getBytes();
			out.write(data);//写表单信息
		
			//3.将文件正文部分输出到微信服务器
	        // 把文件以流文件的方式 写入到微信服务器中
			DataInputStream in = null;
			in = new DataInputStream(new FileInputStream(file));
		
			int bytes = 0;
			byte[] bufferOut = new byte[1024 * 8];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
			in.close();
			out.write(end_data);
			in.close();
			
			//4.将结尾部分输出到微信服务器
			byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
			out.write(foot);
			out.flush();
			out.close();

			//5.将微信服务器返回的输入流转换成字符串   定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = null;
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			String line = null;
			//提供了与StringBuffer兼容的api，当字符缓冲区被单个线程所使用时，优先使用此类，因为其比StringBuffer要快
			stringBuilder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("读取url响应流异常", e);
		}
		conn.disconnect();//关闭连接
		return stringBuilder.toString();
	}

    
    
    /**
     * @Description: TODO 向微信服务器上传网络素材 
     * @Title: uploadMedia 
     * @param @param upUrl 微信上传素材地址（POST）
     * @param @param mediaFileUrl 网络资源地址
     * @param @return    参数  
     * @return String    返回类型  
     * @throws
     */
    public static String uploadFromInter(String upUrl, String mediaFileUrl) {
        StringBuffer resultStr = null;
//        //拼装url地址
//        String mediaStr = MEDIA_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
//System.out.println("mediaStr:" + mediaStr);
        URL mediaUrl;
        try {
//            String boundary = "----WebKitFormBoundaryOYXo8heIv9pgpGjT";
        	String boundary = String.valueOf(System.currentTimeMillis());
            URL url = new URL(upUrl);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            //让输入输出流开启
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            //使用post方式请求的时候必须关闭缓存
            urlConn.setUseCaches(false);
            //设置请求头的Content-Type属性
            urlConn.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);
            urlConn.setRequestMethod("POST");
            //获取输出流，使用输出流拼接请求体
            OutputStream out = urlConn.getOutputStream();

            //读取文件的数据,构建一个GET请求，然后读取指定地址中的数据
            mediaUrl = new URL(mediaFileUrl);
            HttpURLConnection mediaConn = (HttpURLConnection)mediaUrl.openConnection();
            //设置请求方式
            mediaConn.setRequestMethod("GET");
            //设置可以打开输入流
            mediaConn.setDoInput(true);
            //获取传输的数据类型
            String contentType = mediaConn.getHeaderField("Content-Type");
            //将获取大到的类型转换成扩展名
            String fileExt = judgeType(contentType);
            //获取输入流，从mediaURL里面读取数据
            InputStream in = mediaConn.getInputStream();
            BufferedInputStream bufferedIn = new BufferedInputStream(in);
            //数据读取到这个数组里面
            byte[] bytes = new byte[1024];
            int size = 0;
            //使用outputStream流输出信息到请求体当中去
            out.write(("--"+boundary+"\r\n").getBytes());
            out.write(("Content-Disposition: form-data; name=\"media\";\r\n"
                    + "filename=\""+(new Date().getTime())+fileExt+"\"\r\n"
                            + "Content-Type: "+contentType+"\r\n\r\n").getBytes());
            while( (size = bufferedIn.read(bytes)) != -1) {
                out.write(bytes, 0, size);
            }
            //切记，这里的换行符不能少，否则将会报41005错误
            out.write(("\r\n--"+boundary+"--\r\n").getBytes());

            bufferedIn.close();
            in.close();
            mediaConn.disconnect();

            InputStream resultIn = urlConn.getInputStream();
            InputStreamReader reader = new InputStreamReader(resultIn);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String tempStr = null;
            resultStr = new StringBuffer(); 
            while((tempStr = bufferedReader.readLine()) != null) {
                resultStr.append(tempStr);
            }
            bufferedReader.close();
            reader.close();
            resultIn.close();
            urlConn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultStr.toString();
    }
    /**
     * 通过传过来的contentType判断是哪一种类型
     * @param contentType 获取来自连接的contentType
     * @return
     */
    public static String judgeType(String contentType) {
        String fileExt = "";
        if("image/jpeg".equals(contentType)) {
            fileExt = ".jpg";
        } else if("audio/mpeg".equals(contentType)) {
            fileExt = ".mp3";
        } else if("audio/amr".equals(contentType)) {
            fileExt = ".amr";
        } else if("video/mp4".equals(contentType)) {
            fileExt = ".mp4";
        } else if("video/mpeg4".equals(contentType)) {
            fileExt = ".mp4";
        }
        return fileExt;
    }
    
    /**
     * 3.获取微信的JSSDK配置信息
     * 方法名：getWxConfig</br>
     * 详述：获取微信的配置信息 </br>
     * 开发人员：souvc  </br>
     * 创建时间：2016-1-5  </br>
     * @param request
     * @return 说明返回值含义
     * @throws 说明发生此异常的条件
     */
//    public static Map<String, Object> getWxConfig(HttpServletRequest request) {
//        Map<String, Object> ret = new HashMap<String, Object>();
//
//        String appId = "wxa0064ea657f80062"; // 必填，公众号的唯一标识
//        String secret = "fcc960840df869ad1a46af7993784917";
//
//        String requestUrl = request.getRequestURL().toString();
//        String access_token = "";
//        String jsapi_ticket = "";
//        String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳
//        String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId + "&secret=" + secret;
//        //使用http请求获取access_token
//        JSONObject json = WeiXinUtil.httpRequest(url, "GET", null);
//        System.out.println(" 获取access_token "+json);
//        if (json != null) {
//            //要注意，access_token需要缓存
//            access_token = json.getString("access_token");
//            //根据access_token获取jsapi_ticket
//            url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ access_token + "&type=jsapi";
//            json = WeiXinUtil.httpRequest(url, "GET", null);
//            System.out.println("jsapi_ticket "+json);
//            if (json != null) {
//                jsapi_ticket = json.getString("ticket");
//            }
//        }
//        String signature = "";
//        // 注意这里参数名必须全部小写，且必须有序
//        String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr+ "&timestamp=" + timestamp + "&url=" + requestUrl;
//        try {
//            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//            crypt.reset();
//            crypt.update(sign.getBytes("UTF-8"));
//            signature = byteToHex(crypt.digest());
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        ret.put("appId", appId);
//        ret.put("timestamp", timestamp);
//        ret.put("nonceStr", nonceStr);
//        ret.put("signature", signature);
//        return ret;
//    }
//    /**
//     * 方法名：byteToHex</br>
//     * 详述：字符串加密辅助方法 </br>
//     * 开发人员：souvc  </br>
//     * 创建时间：2016-1-5  </br>
//     * @param hash
//     * @return 说明返回值含义
//     * @throws 说明发生此异常的条件
//     */
//    private static String byteToHex(final byte[] hash) {
//        Formatter formatter = new Formatter();
//        for (byte b : hash) {
//            formatter.format("%02x", b);
//        }
//        String result = formatter.toString();
//        formatter.close();
//        return result;
//
//    }
}
