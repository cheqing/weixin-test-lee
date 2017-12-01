package com.iyunr.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName="myFilter", urlPatterns="/*")
public class MyFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

	@Override
	public void destroy() {
		System.out.println("过滤器销毁");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("执行过滤器");
		HttpServletRequest httpReq = (HttpServletRequest)request;
//		HttpServletResponse httpRes = (HttpServletResponse)response;
		String ctxPath = httpReq.getContextPath();
		String requestUri = httpReq.getRequestURI();
		Map<String, String[]> reqParam = httpReq.getParameterMap();
		logger.debug("ctxPath : "+ctxPath+"---------requestUri : "+requestUri+"---------reqParam : "+reqParam);
		// 设定网页的到期时间，一旦过期则必须到服务器上重新调用
//		httpRes.setDateHeader("Expires", -1);
        // Cache-Control 指定请求和响应应遵循的缓存机制 no-cache指示请求或响应消息是不能缓存的
//		httpRes.setHeader("Cache-Control", "no-cache");
        // 用于设定禁止浏览器从本地缓存中调用页面内容，设定后一旦离开页面就无法从Cache中再调出
//		httpRes.setHeader("Pragma", "no-cache");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("过滤器初始化");
		
	}

}
